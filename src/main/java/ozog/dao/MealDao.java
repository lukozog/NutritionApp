package ozog.dao;


import org.hibernate.SessionFactory;
import ozog.model.Meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public class MealDao {

    @Autowired
    private SessionFactory sessionFactory;


    public void createMeal(Meal meal) {
        sessionFactory.getCurrentSession()
                .save(meal);
    }

    public void updateMeal(Meal meal) {
        sessionFactory.getCurrentSession()
                .update(meal);
    }

    public void deleteMeal(long mealId) {
        sessionFactory.getCurrentSession().delete(getMealById(mealId));

    }

    public Meal getMealById(long mealId) {
        return (Meal) sessionFactory.getCurrentSession()
                .get(Meal.class, mealId);
    }

    public List<Meal> getAllMeals() {
        return sessionFactory.getCurrentSession()
                .createCriteria(Meal.class).list();
    }

    public List<Meal> getMealsForUser(long userId) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Meal where user.userId=:userId")
                .setParameter("userId", userId).list();
    }
}
