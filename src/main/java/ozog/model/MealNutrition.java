package ozog.model;


import javax.persistence.*;

@Entity
@Table(name = "MEALNUTRITIONS")
public class MealNutrition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "meal_nutrition_id")
    private long mealNutritionId;

    @Column(name = "meal_nutrition_value")
    private double mealNutritionValue;

    @Column(name = "meal_nutrition_name")
    private String mealNutritionName;

    @Column(name = "meal_nutrition_calories")
    private double mealNutritionCalories;

    @Column(name = "meal_nutrition_proteins")
    private double mealNutritionProteins;

    @Column(name = "meal_nutrition_carbs")
    private double mealNutritionCarbs;

    @Column(name = "meal_nutrition_fat")
    private double mealNutritionFat;

    @ManyToOne
    @JoinColumn(name = "meal_id")
    private Meal meal;

    @ManyToOne
    @JoinColumn(name = "nutrition_id")
    private Nutrition nutrition;

    public MealNutrition(){}

    public MealNutrition(double mealNutritionValue, String mealNutritionName, double mealNutritionCalories, double mealNutritionProteins, double mealNutritionCarbs, double mealNutritionFat, Meal meal, Nutrition nutrition) {
        this.mealNutritionValue = mealNutritionValue;
        this.mealNutritionName = mealNutritionName;
        this.mealNutritionCalories = mealNutritionCalories;
        this.mealNutritionProteins = mealNutritionProteins;
        this.mealNutritionCarbs = mealNutritionCarbs;
        this.mealNutritionFat = mealNutritionFat;
        this.meal = meal;
        this.nutrition = nutrition;
    }

    public long getMealNutritionId() {
        return mealNutritionId;
    }

    public void setMealNutritionId(long mealNutritionId) {
        this.mealNutritionId = mealNutritionId;
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public Nutrition getNutrition() {
        return nutrition;
    }

    public void setNutrition(Nutrition nutrition) {
        this.nutrition = nutrition;
    }

    public double getMealNutritionValue() {
        return mealNutritionValue;
    }

    public void setMealNutritionValue(double mealNutritionValue) {
        this.mealNutritionValue = mealNutritionValue;
    }

    public String getMealNutritionName() {
        return mealNutritionName;
    }

    public void setMealNutritionName(String mealNutritionName) {
        this.mealNutritionName = mealNutritionName;
    }

    public double getMealNutritionCalories() {
        return mealNutritionCalories;
    }

    public void setMealNutritionCalories(double mealNutritionCalories) {
        this.mealNutritionCalories = mealNutritionCalories;
    }

    public double getMealNutritionProteins() {
        return mealNutritionProteins;
    }

    public void setMealNutritionProteins(double mealNutritionProteins) {
        this.mealNutritionProteins = mealNutritionProteins;
    }

    public double getMealNutritionCarbs() {
        return mealNutritionCarbs;
    }

    public void setMealNutritionCarbs(double mealNutritionCarbs) {
        this.mealNutritionCarbs = mealNutritionCarbs;
    }

    public double getMealNutritionFat() {
        return mealNutritionFat;
    }

    public void setMealNutritionFat(double mealNutritionFat) {
        this.mealNutritionFat = mealNutritionFat;
    }

    @Override
    public String toString() {
        return "MealNutrition{" +
                "mealNutritionId=" + mealNutritionId +
                ", mealNutritionValue=" + mealNutritionValue +
                ", mealNutritionName='" + mealNutritionName + '\'' +
                ", mealNutritionCalories=" + mealNutritionCalories +
                ", mealNutritionProteins=" + mealNutritionProteins +
                ", mealNutritionCarbs=" + mealNutritionCarbs +
                ", mealNutritionFat=" + mealNutritionFat +
                ", meal=" + meal +
                ", nutrition=" + nutrition +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MealNutrition that = (MealNutrition) o;

        if (mealNutritionId != that.mealNutritionId) return false;
        if (Double.compare(that.mealNutritionValue, mealNutritionValue) != 0) return false;
        if (Double.compare(that.mealNutritionCalories, mealNutritionCalories) != 0) return false;
        if (Double.compare(that.mealNutritionProteins, mealNutritionProteins) != 0) return false;
        if (Double.compare(that.mealNutritionCarbs, mealNutritionCarbs) != 0) return false;
        if (Double.compare(that.mealNutritionFat, mealNutritionFat) != 0) return false;
        if (mealNutritionName != null ? !mealNutritionName.equals(that.mealNutritionName) : that.mealNutritionName != null)
            return false;
        if (meal != null ? !meal.equals(that.meal) : that.meal != null) return false;
        return nutrition != null ? nutrition.equals(that.nutrition) : that.nutrition == null;
    }
}
