package com.example.capstone2.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
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
@Table(name = "Patient")
public class User {

//id Unique identifier
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Integer userId;

//name: Full
@NotEmpty(message = "Full Name should not be Empty!")
@Size(min=4 , message ="Name Length should be more than 3")
@Pattern(regexp="^[a-zA-Z]*$",message ="Full Name Must contain only characters (no numbers)")
//@Column(columnDefinition = "varchar(255) not null check (name regexp '^[a-zA-Z]*$')")
private String fullName;

//age: Age
//@NotNull(message = "Age should not be Null!")
//@Positive
////@Min(value=18)
////@Column(columnDefinition = "int not null check(age >= 18)")

@Column(columnDefinition = "int not null")
private int age;

@NotNull(message = "Date of birth date should not be Empty!")
@JsonFormat(pattern= "yyyy-MM-dd")
@PastOrPresent
@Column(columnDefinition = "datetime not null")
private LocalDate dateOfBirth;

//gender: Gender
@NotEmpty(message = "Gender should not be Empty!")
@Pattern(regexp = "^(Male|Female)$", message = "Two valid inputs only, Male or Female" )
//@Column(columnDefinition = "varchar(20) not null check(role='Male' or role='Female')")
private String gender;

//role
@NotEmpty(message = "Role should not be Empty!")
@Pattern(regexp = "^(Patient)$" , message = "Role has one valid inputs only, Patient!")
//@Column(columnDefinition = "varchar(20) not null check(role='Patient')")
private String role;

//email: Contact information
@Email
@NotEmpty(message = "Email should not be Empty!")
@Column(columnDefinition = "varchar(50) not null unique")
private String email;


//password: Encrypted user password
@NotEmpty(message = "Password should not be Empty!")
@Pattern(regexp = "^((?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{7,})$" , message = "Password must have to be more than 6 length long,characters and digits")
//@Column(columnDefinition = "varchar(50) not null check (password regexp '^((?=.*[A-Za-z])(?=.*\\\\d)[A-Za-z\\\\d]{7,})$ ' )")
private String password;


@Column(columnDefinition = "boolean default false" )
private boolean hasInsurance;

@Column(columnDefinition = "varchar(50) not null ")
private String insuranceClass;

// registration_date
@Column(columnDefinition = "datetime default (current_timestamp)")
 private LocalDate registrationDate;

//updated_at: Timestamp for the last profile update.
@Column(columnDefinition = "datetime default (current_timestamp)")
private LocalDate updatedAt;

}
