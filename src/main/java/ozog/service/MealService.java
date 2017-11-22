package ozog.service;

import ozog.dao.MealDao;
import ozog.model.Meal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MealService {

    @Autowired
    private MealDao mealDao;


    public void createMeal(Meal meal) {
        mealDao.createMeal(meal);
    }

    public void updateMeal(Meal meal) {
        mealDao.updateMeal(meal);
    }

    public void deleteMeal(long mealId) {
        mealDao.deleteMeal(mealId);
    }

    public Meal getMealById(long mealId) {
        return mealDao.getMealById(mealId);
    }

    public List<Meal> getAllMeals() {
        return mealDao.getAllMeals();
    }

    public List<Meal> getMealsForUser(long userId) {
        return mealDao.getMealsForUser(userId);
    }
}
