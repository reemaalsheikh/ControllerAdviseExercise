package com.example.capstone2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class HealthData {

//id: Unique identifier.
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY )
private Integer id;

//user_id: Foreign key to the User table.
@NotNull(message = "User Id should not be Null!")
@Column(columnDefinition = "int not null ")
private Integer userId;


    //height: Height of the user
    @NotNull(message = "Height should not be Null!")
    @Positive
    @Column(columnDefinition = "DECIMAL(5,2) not null")
    private double height;

    //weight: Weight of the user
    @NotNull(message = "Weight should not be Null!")
    @Positive
    @Column(columnDefinition = "DECIMAL(5,2) not null")
    private double weight;

    //activity_level: Activity level (sedentary, active, very active)
    @NotEmpty(message = "Activity level should not be Empty!")
    @Pattern(regexp = "^(sedentary|active|very active)$", message = "Activity Levels has 3 valid inputs only,(sedentary, active, very active).")
   // @Column(columnDefinition = "varchar(20) not null check(activityLevel='sedentary' or activityLevel='active' or activityLevel='very active')")
    private String activityLevel;

    //dietary_preference: Dietary preferences (vegetarian, vegan, etc.)
    @NotEmpty(message = "Dietary preference should not be Empty!")
    private String dietaryPreference;

    //existing_conditions: Any existing health conditions (e.g., diabetes, hypertension)
    @NotEmpty(message = "Existing Conditions should not be Empty!")
    @Column(columnDefinition = "varchar(50) not null ")
    private String existingConditions;


    //lifestyle_habits: Smoking.
    @NotEmpty(message = "Lifestyle Habits should not be Empty!")
    @Column(columnDefinition = "varchar(50) not null ")
    private String lifestyleHabits;

    //medical_history: History of chronic diseases in the family.
    @NotEmpty(message = "Medical History should not be Empty!")
    @Column(columnDefinition = "varchar(50) not null ")
    private String medicalHistory;


//exercise log: Log of daily exercise (type, duration, intensity).
@NotEmpty(message = "Exercise log should not be Empty!")
@Column(columnDefinition = "varchar(50) not null")
private String exerciseLog;


@Column(columnDefinition = "DOUBLE not null")
private double BMR;

//diet log: Record of daily diet (calories, macronutrients).
//@NotEmpty(message = "Diet log should not be Empty!")
@Column(columnDefinition = "DOUBLE not null")
private double calories;

//sleep_data: Sleep duration and quality.
@NotEmpty(message = "Sleep data should not be Empty!")
@Column(columnDefinition = "varchar(50) not null")
private String sleepData;

@Column(columnDefinition = "DOUBLE not null")
private double sleepHours;


//water_intake: Daily water consumption.
//@NotNull(message = "waterIntake should not be Null!")
@Column(columnDefinition = "DOUBLE not null")
private double waterIntake;


//data_date: The date when the data was recorded.
@Column(columnDefinition = "datetime default (current_timestamp)")
private LocalDate dataCreatedAt;

@Column(columnDefinition = "datetime default (current_timestamp)")
 private LocalDate updatedAt;


}
