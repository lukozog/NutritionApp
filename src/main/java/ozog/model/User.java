package ozog.model;


import org.springframework.cglib.core.Local;
import ozog.util.LocalDateConverter;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private long userId;

    @Column(name = "user_name")
    private String userName;


    @Convert(converter = LocalDateConverter.class)
    @Column(name = "user_birthday", columnDefinition = "Date")
    private LocalDate userBirthday;

    @Column(name = "user_weight")
    private double userWeight;

    @Column(name = "user_height")
    private double userHeight;

    @Column(name = "user_sex")
    private String userSex;

    @Column(name = "user_mail")
    private String userMail;

    @OneToMany(mappedBy = "user")
    private List<Meal> meals;

    public User(){
        this.userBirthday=LocalDate.now();
    }

    public User(String userName, LocalDate userBirthday, double userWeight, double userHeight, String userSex, String userMail, List<Meal> meals) {
        this.userName = userName;
        this.userBirthday = userBirthday;
        this.userWeight = userWeight;
        this.userHeight = userHeight;
        this.userSex = userSex;
        this.userMail = userMail;
        this.meals = meals;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public LocalDate getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(LocalDate userBirthday) {
        this.userBirthday = userBirthday;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public double getUserWeight() {
        return userWeight;
    }

    public void setUserWeight(double userWeight) {
        this.userWeight = userWeight;
    }

    public double getUserHeight() {
        return userHeight;
    }

    public void setUserHeight(double userHeight) {
        this.userHeight = userHeight;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

}
