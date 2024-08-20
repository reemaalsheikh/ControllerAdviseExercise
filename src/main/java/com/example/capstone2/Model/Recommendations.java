package com.example.capstone2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Recommendations {

//id: Unique identifier.
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

//recommendation_text: The personalized recommendation (e.g., "Increase fiber intake").
@NotEmpty(message = "Recommendations text should not be Empty!")
@Column(columnDefinition = "varchar(500) not null")
private String recommendationText;

//type: Type of recommendation (diet, exercise, mental health, etc.).
@NotEmpty(message = "Type should not be Empty!")
@Column(columnDefinition = "varchar(50) not null")
private String type;

    @Column(columnDefinition = "DOUBLE not null")
    private double recommendationFee;

//created_at: Timestamp when the recommendation was made.
@Column(columnDefinition = "datetime default (current_timestamp)")
private LocalDate createdAt;

    //updated_at: Timestamp for the last update.
 @Column(columnDefinition = "datetime default (current_timestamp)")
private LocalDate updatedAt;


//valid_until: Expiration of the recommendation.
//@NotNull(message = "The valid until date cannot be null")
@Future(message = "The valid until date must be in the future!")
@Column(columnDefinition = "datetime not null")
private LocalDate validUntil;



}
