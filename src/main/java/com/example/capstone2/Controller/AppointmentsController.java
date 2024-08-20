package com.example.capstone2.Controller;

import com.example.capstone2.Api.ApiResponse;
import com.example.capstone2.Model.Appointments;
import com.example.capstone2.Service.AppointmentsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/appointments")
public class AppointmentsController {
    private final AppointmentsService appointmentsService;

    //Get all appointments
    @GetMapping("/get")
    public ResponseEntity getAllAppointments() {
        return ResponseEntity.status(200).body(appointmentsService.getAllAppointments());
    }

    //add new appointment
    @PostMapping("/add")
    public ResponseEntity addNewAppointment(@Valid @RequestBody Appointments appointments) {

       appointmentsService.addAppointment(appointments);
        return ResponseEntity.status(200).body(new ApiResponse("Appointments Successfully added!"));
    }

    // Update Appointments
    @PutMapping("/update/{id}")
    public  ResponseEntity updateAppointments(@Valid @RequestBody Appointments appointments ,@PathVariable Integer id){

       appointmentsService.updateAppointment(appointments,id);
        return ResponseEntity.status(200).body(new ApiResponse("Appointments Successfully updated!"));
    }


    //Delete Appointments
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteAppointments (@PathVariable Integer id){
        appointmentsService.deleteAppointment(id);
        return ResponseEntity.status(200).body(new ApiResponse("Appointments Successfully deleted!"));
    }

    @GetMapping("/fee/{userId}/{id}")
    public ResponseEntity AppointmentFee(@PathVariable Integer userId,@PathVariable Integer id){
       double appointmentFee = appointmentsService.AppointmentFee(userId,id);
        return ResponseEntity.status(200).body("Appointments Fee=" + " " + appointmentFee);
    }

    @GetMapping("/appointments/{doctorId}")
    public ResponseEntity getAllAppointmentsByDoctorId (@PathVariable Integer doctorId){
        return ResponseEntity.status(200).body(appointmentsService.getAppointmentsByDoctorId(doctorId));
    }




}
