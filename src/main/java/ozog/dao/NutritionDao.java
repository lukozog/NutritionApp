package ozog.dao;

import ozog.model.Nutrition;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NutritionDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void createNutrition(Nutrition nutrition) {
        sessionFactory.getCurrentSession().save(nutrition);
    }

    public void updateNutrition(Nutrition nutrition) {
        sessionFactory.getCurrentSession().update(nutrition);
    }

    public void deleteNutrition(long nutritionId) {
        sessionFactory.getCurrentSession().delete(getNutritionById(nutritionId));
    }

    public Nutrition getNutritionById(long nutritionId) {
        return (Nutrition) sessionFactory.getCurrentSession().get(Nutrition.class, nutritionId);
    }

    public List<Nutrition> getAllNutritions() {
        return (List<Nutrition>) sessionFactory.getCurrentSession().createCriteria(Nutrition.class).list();
    }

}
