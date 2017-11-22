package ozog.model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "NUTRITION")
public class Nutrition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "nutrition_id")
    private long nutritionId;

    @Column(name = "nutrition_name")
    private String nutritionName;

    @Column(name = "nutrition_calories")
    private double nutritionCalories;

    @Column(name = "nutrition_proteins")
    private double nutritionProteins;

    @Column(name = "nutrition_carbs")
    private double nutritionCarbs;

    @Column(name = "nutrition_fat")
    private double nutritionFat;

    @OneToMany(mappedBy = "nutrition")
    private List<MealNutrition> mealNutritions;

    public Nutrition(){}

    public Nutrition(String nutritionName, double nutritionCalories, double nutritionProteins, double nutritionCarbs, double nutritionFat, List<MealNutrition> mealNutritions) {
        this.nutritionName = nutritionName;
        this.nutritionCalories = nutritionCalories;
        this.nutritionProteins = nutritionProteins;
        this.nutritionCarbs = nutritionCarbs;
        this.nutritionFat = nutritionFat;
        this.mealNutritions = mealNutritions;
    }

    public long getNutritionId() {
        return nutritionId;
    }

    public void setNutritionId(long nutritionId) {
        this.nutritionId = nutritionId;
    }

    public String getNutritionName() {
        return nutritionName;
    }

    public void setNutritionName(String nutritionName) {
        this.nutritionName = nutritionName;
    }

    public double getNutritionCalories() {
        return nutritionCalories;
    }

    public void setNutritionCalories(double nutritionCalories) {
        this.nutritionCalories = nutritionCalories;
    }

    public double getNutritionProteins() {
        return nutritionProteins;
    }

    public void setNutritionProteins(double nutritionProteins) {
        this.nutritionProteins = nutritionProteins;
    }

    public double getNutritionCarbs() {
        return nutritionCarbs;
    }

    public void setNutritionCarbs(double nutritionCarbs) {
        this.nutritionCarbs = nutritionCarbs;
    }

    public double getNutritionFat() {
        return nutritionFat;
    }

    public void setNutritionFat(double nutritionFat) {
        this.nutritionFat = nutritionFat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Nutrition nutrition = (Nutrition) o;

        if (nutritionId != nutrition.nutritionId) return false;
        if (Double.compare(nutrition.nutritionCalories, nutritionCalories) != 0) return false;
        if (Double.compare(nutrition.nutritionProteins, nutritionProteins) != 0) return false;
        if (Double.compare(nutrition.nutritionCarbs, nutritionCarbs) != 0) return false;
        if (Double.compare(nutrition.nutritionFat, nutritionFat) != 0) return false;
        if (nutritionName != null ? !nutritionName.equals(nutrition.nutritionName) : nutrition.nutritionName != null)
            return false;
        return mealNutritions != null ? mealNutritions.equals(nutrition.mealNutritions) : nutrition.mealNutritions == null;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
