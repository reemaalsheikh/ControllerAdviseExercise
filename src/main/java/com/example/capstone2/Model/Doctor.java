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
//@Table(name = "Doctors")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Integer doctorId;

    @NotEmpty(message = "First Name should not be Empty!")
    @Size(min=4 , message ="First Name Length should be more than 3")
    @Pattern(regexp="^[a-zA-Z]*$",message ="First Name Must contain only characters (no numbers)")
//@Column(columnDefinition = "varchar(255) not null check (name regexp '^[a-zA-Z]*$')")
    private String firstName;

    @NotEmpty(message = "Last Name should not be Empty!")
    @Size(min=4 , message ="Last Name Length should be more than 3")
    @Pattern(regexp="^[a-zA-Z]*$",message ="Last Name Must contain only characters (no numbers)")
//@Column(columnDefinition = "varchar(255) not null check (name regexp '^[a-zA-Z]*$')")
    private String lastName;


    @NotNull(message = "Date of birth date should not be Empty!")
    @JsonFormat(pattern= "yyyy-MM-dd")
    @PastOrPresent
    @Column(columnDefinition = "datetime not null")
    private LocalDate dob;

    @NotEmpty(message = "Gender should not be Empty!")
    @Pattern(regexp = "^(Male|Female)$", message = "Gender has two valid inputs only, Male or Female" )
  //@Column(columnDefinition = "varchar(20) not null check(gender='Male' or gender='Female')")
    private String gender;

    @Email
    @NotEmpty(message = "Email should not be Empty!")
    @Column(columnDefinition = "varchar(50) not null unique")
    private String email;

    //password: Encrypted user password
    @NotEmpty(message = "Password should not be Empty!")
    @Pattern(regexp = "^((?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{7,})$" , message = "Password must have to be more than 6 length long,characters and digits")
//@Column(columnDefinition = "varchar(50) not null check (password regexp '^((?=.*[A-Za-z])(?=.*\\\\d)[A-Za-z\\\\d]{7,})$ ' )")
    private String password;

    @NotEmpty(message = "Specialization should not be Empty!")
    @Column(columnDefinition = "varchar(50) not null")
    private String specialization;

    @NotEmpty(message = "licenseNumber should not be Empty!")
    @Column(columnDefinition = "varchar(50) not null")
    private String licenseNumber;

    @NotEmpty(message = "Years Of Experience should not be Empty!")
    @Column(columnDefinition = "varchar(50) not null")
    private String yearsOfExperience;

    // registration_date
    @Column(columnDefinition = "datetime default (current_timestamp)")
    private LocalDate registrationDate;

    //updated_at: Timestamp for the last profile update.
   @Column(columnDefinition = "datetime default (current_timestamp)")
    private LocalDate updatedAt;




}
