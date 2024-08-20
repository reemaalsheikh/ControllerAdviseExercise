package com.example.capstone2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "Chronic Disease Prediction")
public class CDPrediction {

//    id: Unique identifier.
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY )
private Integer id;

//user_id: Foreign key to the User table.
@NotNull(message = "User Id should not be Null!")
@Column(columnDefinition = "int not null ")
private Integer userId;

 @NotNull(message = "Doctor Id should not be Null!")
 @Column(columnDefinition = "int not null ")
 private Integer doctorId;

//blood_pressure: Blood pressure readings.
@Column(columnDefinition = "int not null")
private int bloodPressure;


//Blood sugar: Blood sugar levels.
@Column(columnDefinition = "int not null")
private int bloodSugar;


@Column(columnDefinition = "int not null")
private int cholesterolLevel;

//stress_levels: Self-reported stress levels.
@NotEmpty(message = "Stress level should not be Empty!")
@Pattern(regexp = "^(Relaxed|Occasional|Frequent|Chronic)$", message = "Four valid inputs only Relaxed, Occasional, Frequent,and Chronic" )
//@Column(columnDefinition = "varchar(500) not null check(stressLevel='Relaxed' or stressLevel='Occasional' or stressLevel='Frequent' or stressLevel='Chronic')")
private String stressLevel;

//predicted_condition: The chronic condition predicted (e.g., Type 2 Diabetes).
@NotEmpty(message = "Predicted Conditions should not be Empty!")
@Column(columnDefinition = "varchar(255) not null")
private String predictedCondition;


//recommendations: Steps to reduce risk (linked to Recommendations table).
@NotEmpty(message = "Recommendations should not be Empty!")
@Column(columnDefinition = "varchar(800) not null")
private String recommendations;

//created_at: Timestamp when the prediction was made.
@Column(columnDefinition = "datetime default (current_timestamp)")
private LocalDate createdAt;

//updated_at: Timestamp for the last update.
 @Column(columnDefinition = "datetime default (current_timestamp)")
private LocalDate updatedAt;

}
