package ozog.dao;

import ozog.model.MealNutrition;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MealNutritionDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void createMealNutrition(MealNutrition mealNutrition) {
        sessionFactory.getCurrentSession().save(mealNutrition);
    }

    public void deleteMealNutrition(long mealNutritionId) {
        sessionFactory.getCurrentSession().delete(getMealNutritionById(mealNutritionId));
    }

    public List<MealNutrition> getMealNutritions() {
        return (List<MealNutrition>) sessionFactory.getCurrentSession().createCriteria(MealNutrition.class).list();
    }

    public List<MealNutrition> getMealNutritions(long mealId) {
        Query query = sessionFactory.getCurrentSession().createQuery("from MealNutrition where meal.mealId=:mealId");
        query.setParameter("mealId", mealId);
        return query.list();
    }

    public MealNutrition getMealNutritionById(long mealNutritionId) {
        return (MealNutrition) sessionFactory.getCurrentSession().get(MealNutrition.class, mealNutritionId);
    }

    public MealNutrition getLastMealNutritionById(long mealId) {
        Query query = sessionFactory.getCurrentSession().createQuery("from MealNutrition where meal.mealId=:mealId order by 1 desc");
        query.setParameter("mealId", mealId);
        query.setMaxResults(1);
        return (MealNutrition) query.uniqueResult();

    }
}
