package ozog.model;

import ozog.util.LocalDateConverter;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "MEAL")
public class Meal {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "meal_id")
    private long mealId;

    @Column(name = "meal_name")
    private String mealName;

    @Convert(converter = LocalDateConverter.class)
    @Column(name = "meal_date", columnDefinition = "Date")
    private LocalDate mealDate;

    @Column(name = "meal_calories")
    private double mealCalories;

    @Column(name = "meal_proteins")
    private double mealProteins;

    @Column(name = "meal_carbs")
    private double mealCarbs;

    @Column(name = "meal_fat")
    private double mealFat;

    @ManyToOne
    @JoinColumn(name = "meal_user_id")
    private User user;

    public Meal() {
        this.mealDate = LocalDate.now();
    }

    public Meal(String mealName, LocalDate mealDate, double mealCalories, double mealProteins, double mealCarbs, double mealFat, User user) {
        this.mealName = mealName;
        this.mealDate = mealDate;
        this.mealCalories = mealCalories;
        this.mealProteins = mealProteins;
        this.mealCarbs = mealCarbs;
        this.mealFat = mealFat;
        this.user = user;
    }

    public long getMealId() {
        return mealId;
    }

    public void setMealId(long mealId) {
        this.mealId = mealId;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public LocalDate getMealDate() {
        return mealDate;
    }

    public void setMealDate(LocalDate mealDate) {
        this.mealDate = mealDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getMealCalories() {
        return mealCalories;
    }

    public void setMealCalories(double mealCalories) {
        this.mealCalories = mealCalories;
    }


    public double getMealProteins() {
        return mealProteins;
    }

    public void setMealProteins(double mealProteins) {
        this.mealProteins = mealProteins;
    }

    public double getMealCarbs() {
        return mealCarbs;
    }

    public void setMealCarbs(double mealCarbs) {
        this.mealCarbs = mealCarbs;
    }

    public double getMealFat() {
        return mealFat;
    }

    public void setMealFat(double mealFat) {
        this.mealFat = mealFat;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "mealId=" + mealId +
                ", mealName='" + mealName + '\'' +
                ", mealDate=" + mealDate +
                ", mealCalories=" + mealCalories +
                ", mealProteins=" + mealProteins +
                ", mealCarbs=" + mealCarbs +
                ", mealFat=" + mealFat +
                ", user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Meal meal = (Meal) o;

        if (mealId != meal.mealId) return false;
        if (Double.compare(meal.mealCalories, mealCalories) != 0) return false;
        if (Double.compare(meal.mealProteins, mealProteins) != 0) return false;
        if (Double.compare(meal.mealCarbs, mealCarbs) != 0) return false;
        if (Double.compare(meal.mealFat, mealFat) != 0) return false;
        if (mealName != null ? !mealName.equals(meal.mealName) : meal.mealName != null) return false;
        if (mealDate != null ? !mealDate.equals(meal.mealDate) : meal.mealDate != null) return false;
        return user != null ? user.equals(meal.user) : meal.user == null;
    }

}
