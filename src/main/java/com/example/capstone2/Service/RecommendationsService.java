package com.example.capstone2.Service;

import com.example.capstone2.Api.ApiException;

import com.example.capstone2.Model.*;
import com.example.capstone2.Repsitory.CDPredictionRepository;
import com.example.capstone2.Repsitory.DoctorRepository;
import com.example.capstone2.Repsitory.RecommendationsRepository;
import com.example.capstone2.Repsitory.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendationsService {
    private final RecommendationsRepository recommendationsRepository;
    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;
    private final CDPredictionRepository cdPredictionRepository;


    // Get all Recommendations
    public List<Recommendations> getAllRecommendations() {
        return recommendationsRepository.findAll();
    }

    // Add newRecommendations
    public void addRecommendations (Recommendations recommendations) {
        User uId = userRepository.findUserByUserId(recommendations.getUserId());
        if (uId == null) {
            throw new ApiException("User id is not valid! Please try again!");
        }

        Doctor doc = doctorRepository.findDoctorByDoctorId(recommendations.getDoctorId());
        if(doc == null){
            throw new ApiException("Doctor id is not valid, Only doctors can add Chronic Disease Prediction!");
        }
        recommendations.setCreatedAt(LocalDate.now());
        recommendations.setUpdatedAt(LocalDate.now());
        recommendations.setValidUntil(LocalDate.now().plusMonths(6));
        recommendationsRepository.save(recommendations);

    }


    // Update Recommendation
    public void updateRecommendations (Recommendations recommendations,Integer id) {
        // Check if the user exists
        User uId = userRepository.findUserByUserId(recommendations.getUserId());
        if (uId == null) {
            throw new ApiException("User id is not valid! Please try again!");
        }

        Doctor doc = doctorRepository.findDoctorByDoctorId(recommendations.getDoctorId());
        if(doc == null){
            throw new ApiException("Doctor id is not valid, Only doctors can add Chronic Disease Prediction!");
        }
        // Check if the Recommendation id exists
        Recommendations r = recommendationsRepository.findRecommendationsById(id);
        if (r == null) {
            throw new ApiException("Recommendations id is not valid!");
        }
        r.setRecommendationText(recommendations.getRecommendationText());
        r.setType(recommendations.getType());
        r.setUpdatedAt(LocalDate.now());
        r.setValidUntil(LocalDate.now().plusMonths(6));
          recommendationsRepository.save(r);
    }


    // Delete Recommendations
    public void deleteRecommendations(Integer id) {
        Recommendations r = recommendationsRepository.findRecommendationsById(id);
        if (r == null) {
            throw new ApiException("Recommendations id is not valid!");
        }
        recommendationsRepository.delete(r);
    }



    public Recommendations getRecommendations(Integer CDid, Integer recId) {
        CDPrediction cd = cdPredictionRepository.findCDPredictionById(CDid);
        if (cd == null) {
            throw new ApiException("CD prediction id is not valid!");
        }
        Recommendations recommendations = recommendationsRepository.findRecommendationsById(recId);
        if (recommendations == null) {
            throw new ApiException("Recommendations id is not valid!");
        }
        recommendations.setRecommendationText(cd.getRecommendations());
        recommendationsRepository.save(recommendations);
        return recommendations;
    }

    public Recommendations RecommendationFee(Integer userId, Integer recommendationId) {
        // Fixed fee amount
        double fee = 30.0;

        User user = userRepository.findUserByUserId(userId);
        if (user == null) {
            throw new ApiException("User id not found, please try again with a valid id");
        }

        Recommendations recommendation = recommendationsRepository.findRecommendationsById(recommendationId);
        if (recommendation == null) {
            throw new ApiException("Recommendation id not found!");
        }

        // Set the recommendation fee
        recommendation.setRecommendationFee(fee);
        recommendationsRepository.save(recommendation);

        return recommendation;
    }


}
