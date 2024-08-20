package com.example.capstone2.Controller;

import com.example.capstone2.Api.ApiResponse;
import com.example.capstone2.Model.Doctor;
import com.example.capstone2.Service.DoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/doctor")
public class DoctorController {
    private final DoctorService doctorService;

    // Get all the Doctors
    @GetMapping("/get")
    public ResponseEntity getAllDoctors() {
        return ResponseEntity.status(200).body(doctorService.getAlldoctors());
    }

    // Add new Doctor
    @PostMapping("/add")
    public ResponseEntity addNewDoctor(@Valid @RequestBody Doctor doctor) {

        doctorService.addDoctor(doctor);
        return ResponseEntity.status(200).body(new ApiResponse("Doctor Successfully added!"));
    }

    // Update Doctors
    @PutMapping("/update/{doctorId}")
    public  ResponseEntity updateDoctor(@PathVariable Integer doctorId,@Valid @RequestBody Doctor doctor ){

        doctorService.updateDoctor(doctorId,doctor);
        return ResponseEntity.status(200).body(new ApiResponse("Doctor Successfully updated!"));
    }

    // Delete Doctors
    @DeleteMapping("/delete/{doctorId}")
    public ResponseEntity deleteDoctors(@PathVariable Integer doctorId){
        doctorService.deleteDoctor(doctorId);
        return ResponseEntity.status(200).body(new ApiResponse("Doctor Successfully deleted!"));
    }


}
