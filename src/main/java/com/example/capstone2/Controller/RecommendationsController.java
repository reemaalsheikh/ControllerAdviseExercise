package com.example.capstone2.Controller;

import com.example.capstone2.Api.ApiResponse;
import com.example.capstone2.Model.CDPrediction;
import com.example.capstone2.Model.Recommendations;
import com.example.capstone2.Service.RecommendationsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/rec")
public class RecommendationsController {
    private final RecommendationsService recommendationsService;


    //Get All Recommendations
    @GetMapping("/get")
    public ResponseEntity getAllRecommendations() {
        return ResponseEntity.status(200).body(recommendationsService.getAllRecommendations());
    }
    // Add new Recommendation
    @PostMapping("/add")
    public ResponseEntity addNewRecommendations(@Valid @RequestBody Recommendations recommendations) {
       recommendationsService.addRecommendations(recommendations);
        return ResponseEntity.status(200).body(new ApiResponse("Recommendations Successfully added!"));
    }

    // Update Recommendations
    @PutMapping("/update/{id}")
    public  ResponseEntity updateRecommendation(@Valid @RequestBody Recommendations recommendations ,@PathVariable Integer id){

        recommendationsService.updateRecommendations(recommendations,id);
        return ResponseEntity.status(200).body(new ApiResponse("Recommendations Successfully updated!"));
    }

    // Delete Recommendations
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteRecommendations (@PathVariable Integer id){
       recommendationsService.deleteRecommendations(id);
        return ResponseEntity.status(200).body(new ApiResponse("Recommendations Successfully deleted!"));
    }

    @GetMapping("/recommendations/{CDid}/{recId}")
    public ResponseEntity getRecommendations(@PathVariable Integer CDid,@PathVariable Integer recId){
        return ResponseEntity.status(200).body(recommendationsService.getRecommendations(CDid,recId));
    }

    @PutMapping("/recFee/{userId}/{recommendationId}")
    public ResponseEntity RecomendationFee(@PathVariable Integer userId,@PathVariable Integer recommendationId){
        return ResponseEntity.status(200).body(recommendationsService.RecommendationFee(userId,recommendationId));
    }




}
