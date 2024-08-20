package com.example.capstone2.Controller;

import com.example.capstone2.Api.ApiResponse;
import com.example.capstone2.Model.CDPrediction;
import com.example.capstone2.Service.CDPredictionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/CDPrediction")
//Chronic Disease Prediction
public class CDPredictionController {

    private final CDPredictionService cdPredictionService;

 //Get All Chronic Disease Prediction
    @GetMapping("/get")
    public ResponseEntity getAllChronicDP() {
        return ResponseEntity.status(200).body(cdPredictionService.getAllChronicDP());
    }
    // Add new Chronic Disease Prediction
    @PostMapping("/add")
    public ResponseEntity addNewChronicDP(@Valid @RequestBody CDPrediction cdPrediction) {

        cdPredictionService.addChronicDP(cdPrediction);
        return ResponseEntity.status(200).body(new ApiResponse("Chronic Disease Prediction Successfully added!"));
    }

    // Update Chronic Disease Prediction
    @PutMapping("/update/{id}")
    public  ResponseEntity updateChronicDP(@Valid @RequestBody CDPrediction cdPrediction ,@PathVariable Integer id){
       cdPredictionService.updateChronicDP(cdPrediction,id);
        return ResponseEntity.status(200).body(new ApiResponse("Chronic Disease Prediction Successfully updated!"));
    }

    // Delete Chronic Disease Prediction
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteChronicDP (@PathVariable Integer id){
       cdPredictionService.deleteChronicDP(id);
        return ResponseEntity.status(200).body(new ApiResponse("Chronic Disease Prediction Successfully deleted!"));
    }


    //Blood pressure indication
    @GetMapping("/bp/{id}")
    public ResponseEntity bloodPressureIndication (@PathVariable Integer id){

     return ResponseEntity.status(200).body(cdPredictionService.bloodPressureIndication(id));
    }

    //Blood Sugar indication
    @GetMapping("/bs/{id}")
    public ResponseEntity bloodSugarIndication (@PathVariable Integer id){

        return ResponseEntity.status(200).body(cdPredictionService.bloodSugarIndication(id));
    }

    //Cholesterol Level indication
    @GetMapping("/chol/{id}")
    public ResponseEntity cholesterolLevelIndication (@PathVariable Integer id){
        return ResponseEntity.status(200).body(cdPredictionService.cholesterolLevelIndication(id));
    }

    //Stress Level indication
    @GetMapping("/stress/{id}")
    public ResponseEntity stressLevelIndication (@PathVariable Integer id){
        return ResponseEntity.status(200).body(cdPredictionService.stressLevelIndication(id));
    }





}
