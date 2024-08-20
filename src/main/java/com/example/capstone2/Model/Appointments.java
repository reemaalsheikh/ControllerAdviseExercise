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
public class Appointments {

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

//doctor_name: Name of the doctor.
@NotEmpty(message = "Name should not be Empty!")
@Size(min=4 , message ="Name Length should be more than 3")
@Pattern(regexp="^[a-zA-Z]*$",message ="Name Must contain only characters (no numbers)")
//@Column(columnDefinition = "varchar(255) not null check (name regexp '^[a-zA-Z]*$')")
private String name;

//appointment_date: Date and time of the appointment.
@NotNull(message = "Appointment date should not be Empty!")
@JsonFormat(pattern= "yyyy-MM-dd")
@FutureOrPresent
@Column(columnDefinition = "datetime not null")
private LocalDate appointmentDate;


//visitPurpose: Purpose of the visit (e.g., regular check-up, specific concern).
@NotEmpty(message = "Purpose of the visit should not be Empty!")
@Column(columnDefinition = "varchar(500) not null")
private String visitPurpose;

@Column(columnDefinition = "DOUBLE not null")
private double appointmentFee=200;

//created_at: Timestamp when the appointment was scheduled.
@Column(columnDefinition = "datetime default (current_timestamp)")
private LocalDate createdAt;

    //updated_at: Timestamp for the last update.
    @Column(columnDefinition = "datetime default (current_timestamp)")
    private LocalDate updatedAt;


}
