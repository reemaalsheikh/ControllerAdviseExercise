package com.example.capstone2.Controller;

import com.example.capstone2.Api.ApiResponse;
import com.example.capstone2.Model.HealthData;
import com.example.capstone2.Service.HealthDataService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/healthData")
public class HealthDataController {
    private final HealthDataService healthDataService;


    // Get all Health data
    @GetMapping("/get")
    public ResponseEntity getAllhealthData() {

        return ResponseEntity.status(200).body(healthDataService.getAllHealthData());
    }

    // Add new Health data
    @PostMapping("/add")
    public ResponseEntity addNewHealthData(@Valid @RequestBody HealthData healthData) {

        healthDataService.addHealthData(healthData);
        return ResponseEntity.status(200).body(new ApiResponse("Health Data Successfully added!"));
    }

    // Update Health data
    @PutMapping("/update/{id}")
    public  ResponseEntity updateUser(@PathVariable Integer id,@Valid @RequestBody HealthData healthData ){

        healthDataService.updateHealthData(id,healthData);
        return ResponseEntity.status(200).body(new ApiResponse("Health Data Successfully updated!"));
    }

    // Delete Health data
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id){
       healthDataService.deleteHealthData(id);
        return ResponseEntity.status(200).body(new ApiResponse("Health Data Successfully deleted!"));
    }


    @GetMapping("/bmr/{userId}/{id}")
    public ResponseEntity calculateBMR(@PathVariable Integer userId,@PathVariable Integer id){

        return ResponseEntity.status(200).body(healthDataService.calculateBMR(userId,id));
    }
    @GetMapping("/calories/{userId}/{id}")
    public ResponseEntity calculateCalories(@PathVariable Integer userId, @PathVariable Integer id){
        return ResponseEntity.status(200).body(healthDataService.calculateCalories(userId,id));
    }

    @GetMapping("/sleep/{userId}/{id}")
    public ResponseEntity calculateSleepData (@PathVariable Integer userId, @PathVariable Integer id){
        return ResponseEntity.status(200).body(healthDataService.calculateSleepData(userId,id));
    }


    @GetMapping("/water/{userId}/{id}")
    public ResponseEntity calculateWaterIntake (@PathVariable Integer userId, @PathVariable Integer id){
        return ResponseEntity.status(200).body(healthDataService.calculateWaterIntake(userId,id));
    }




}
