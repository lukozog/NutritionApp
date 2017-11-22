package ozog.service;


import ozog.dao.MealNutritionDao;
import ozog.model.Mail;
import ozog.model.Meal;
import ozog.model.MealNutrition;
import ozog.model.Nutrition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MealNutritionService {

    @Autowired
    private MealNutritionDao mealNutritionDao;

    @Autowired
    private MealService mealService;

    @Autowired
    private MailService mailService;

    public void createMealNutrition(MealNutrition mealNutrition) {
        mealNutritionDao.createMealNutrition(calculateValues(mealNutrition));
    }


    public void deleteMealNutrition(long mealNutritionId) {
        mealNutritionDao.deleteMealNutrition(mealNutritionId);
    }


    public List<MealNutrition> getMealNutritions(long mealId) {
        updateMealNutritions(mealNutritionDao.getMealNutritions(mealId), mealId);
        return mealNutritionDao.getMealNutritions(mealId);
    }

    public MealNutrition getLastMealNutrition(long mealId) {
        updateMealNutritions(mealNutritionDao.getMealNutritions(mealId), mealId);
        return mealNutritionDao.getLastMealNutritionById(mealId);
    }


    protected MealNutrition calculateValues(MealNutrition mealNutrition) {
        Nutrition nutrition = mealNutrition.getNutrition();
        double calculateValue = mealNutrition.getMealNutritionValue();

        mealNutrition.setMealNutritionName(nutrition.getNutritionName());
        mealNutrition.setMealNutritionCalories(nutrition.getNutritionCalories() * calculateValue / 100);
        mealNutrition.setMealNutritionProteins(nutrition.getNutritionProteins() * calculateValue / 100);
        mealNutrition.setMealNutritionCarbs(nutrition.getNutritionCarbs() * calculateValue / 100);
        mealNutrition.setMealNutritionFat(nutrition.getNutritionFat() * calculateValue / 100);

        return mealNutrition;
    }

    protected void updateMealNutritions(List<MealNutrition> mealNutritions, long mealId) {
        Meal meal = mealService.getMealById(mealId);
        meal.setMealCalories(mealNutritions.stream()
                .mapToDouble(MealNutrition::getMealNutritionCalories)
                .sum());
        meal.setMealProteins(mealNutritions.stream()
                .mapToDouble(MealNutrition::getMealNutritionProteins)
                .sum());
        meal.setMealCarbs(mealNutritions.stream()
                .mapToDouble(MealNutrition::getMealNutritionCarbs)
                .sum());
        meal.setMealFat(mealNutritions.stream()
                .mapToDouble(MealNutrition::getMealNutritionFat)
                .sum());
    }

    public void sendEmail(long mealId) {
        MealNutrition mealNutrition = getLastMealNutrition(mealId);
        Meal meal = mealService.getMealById(mealId);
        Mail mail = new Mail();
        mail.setMailAddress(meal.getUser().getUserMail());
        mail.setMailTopic("New calories in " + meal.getMealName() + " meal");
        mail.setMailText(mailText(meal, mealNutrition));
        mailService.sendEmail(mail);
    }

    public String mailText(Meal meal, MealNutrition mealNutrition) {
        return "You've just added new nutrition to your meal?\n" +
                mealNutrition.getMealNutritionName() + ": \n" +
                "Cal: " + mealNutrition.getMealNutritionCalories() + " Proteins: " + mealNutrition.getMealNutritionProteins() +
                " Carbs: " + mealNutrition.getMealNutritionCarbs() + " Fat: " + mealNutrition.getMealNutritionFat() + "\n" +
                "As a whole value for meal there will be\n" +
                "Meal Cal: " + Math.round(meal.getMealCalories()) + " Meal Proteins: " + Math.round(meal.getMealProteins()) +
                " Meal Carbs: " + Math.round(meal.getMealCarbs()) + " Meal Fat: " + Math.round(meal.getMealFat()) + "\n" +
                "Cheers, remember we're watching your progress";
    }

}