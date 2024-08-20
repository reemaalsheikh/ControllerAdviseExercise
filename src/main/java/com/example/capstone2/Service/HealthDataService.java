package com.example.capstone2.Service;

import com.example.capstone2.Api.ApiException;
import com.example.capstone2.Model.CDPrediction;
import com.example.capstone2.Model.HealthData;
import com.example.capstone2.Model.User;
import com.example.capstone2.Repsitory.CDPredictionRepository;
import com.example.capstone2.Repsitory.HealthDataRepository;
import com.example.capstone2.Repsitory.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HealthDataService {

    private final HealthDataRepository healthDataRepository;
    private final UserRepository userRepository;
    private final CDPredictionRepository CDPredictionRepository;

    // Get all Health data
    public List<HealthData> getAllHealthData() {
        return healthDataRepository.findAll();
    }

    // Add new Health data
    public void addHealthData (HealthData healthData) {
        User user = userRepository.findUserByUserId(healthData.getUserId());
       if (user == null) {
    throw new ApiException("User id not found!");
    }
        healthData.setDataCreatedAt(LocalDate.now());
        healthData.setUpdatedAt(LocalDate.now());
        healthDataRepository.save(healthData);
    }

    // Update Health data
    public void updateHealthData (Integer id, HealthData healthData) {
        HealthData hd = healthDataRepository.findHealthDataById(id);
        if (hd == null) {
            throw new ApiException("Health data id not found, please try again with a valid id!");
        }
         hd.setUserId(healthData.getUserId());
         hd.setHeight(healthData.getHeight());
         hd.setWeight(healthData.getWeight());
         hd.setActivityLevel(healthData.getActivityLevel());
         hd.setDietaryPreference(healthData.getDietaryPreference());
         hd.setExistingConditions(healthData.getExistingConditions());
         hd.setLifestyleHabits(healthData.getLifestyleHabits());
         hd.setMedicalHistory(healthData.getMedicalHistory());
         hd.setExerciseLog(healthData.getExerciseLog());
         hd.setCalories(healthData.getCalories());
         hd.setSleepData(healthData.getSleepData());
         hd.setWaterIntake(healthData.getWaterIntake());
         hd.setUpdatedAt(LocalDate.now());
         healthDataRepository.save(hd);
    }

    // Delete Health data
    public void deleteHealthData(Integer id) {
        HealthData hd = healthDataRepository.findHealthDataById(id);
        if (hd == null) {
            throw new ApiException("Health data id not found, please try again with a valid id!");
        }
        healthDataRepository.delete(hd);
    }



    public HealthData calculateBMR(Integer userId, Integer id){
        User user = userRepository.findUserByUserId(userId);
        if (user == null) {
            throw new ApiException("User id not found, please try again with a valid id!");
        }
        HealthData bmr = healthDataRepository.calculateBMRByHealthDataId(id);
        if (bmr == null) {
            throw new ApiException("Health Data id not found, please try again with a valid id!");
        }
        double weight = bmr.getWeight(); // in kg
        double height = bmr.getHeight(); // in cm
        int age =  user.getAge();
        String gender = user.getGender();
      //  String activityLevel = bmr.getActivityLevel();
if(age != 0) {
    if (gender.equalsIgnoreCase("male")) {
        bmr.setBMR((10 * weight) + (6.25 * height) - (5 * age) + 5);

    } else if (gender.equalsIgnoreCase("female")) {
        bmr.setBMR((10 * weight) + (6.25 * height) - (5 * age) - 161);
    } else {
        throw new ApiException("Invalid gender specified!");
    }
}else {
    throw new ApiException("Age not found!");

}
        healthDataRepository.save(bmr);
        return bmr;
    }


    public HealthData calculateCalories(Integer userId, Integer id) {
        User user = userRepository.findUserByUserId(userId);
        if (user == null) {
            throw new ApiException("User id not found!");
        }
        HealthData hd = healthDataRepository.findHealthDataById(userId);
        if (hd == null) {
            throw new ApiException("Health data id not found, please try again with a valid id");
        }

        String activityLevel = hd.getActivityLevel();

        double bmr = hd.getBMR();
        double calories ;

        if (activityLevel.equalsIgnoreCase("sedentary")) {
            calories = bmr * 1.2;
        }
        else if (activityLevel.equalsIgnoreCase("active")) {
            calories = bmr * 1.55;
        }
        else if (activityLevel.equalsIgnoreCase("very active")) {
            calories = bmr * 1.725;
        }else{
            throw new ApiException("Invalid activity level specified!");
        }
        // Set the calculated calories back to the user object
        hd.setCalories(calories);
        healthDataRepository.save(hd);
        return hd;
    }



    public HealthData calculateSleepData(Integer userId, Integer id) {
        User user = userRepository.findUserByUserId(userId);
        if (user == null) {
            throw new ApiException("User id not found!");
        }

        HealthData hd = healthDataRepository.findHealthDataById(id);
        if (hd == null) {
            throw new ApiException("Health data id not found, please try again with a valid id");
        }
        CDPrediction cd =CDPredictionRepository.findCDPredictionById(id);
        if (cd == null) {
            throw new ApiException("Chronic Disease Prediction id not found, please try again with a valid id");
        }

        double baseSleepHours = 7.5; // Base sleep hours
        String activityLevel = hd.getActivityLevel();
        String stressLevel =cd.getStressLevel();

        // Adjust sleep hours based on activity level
        if (activityLevel.equalsIgnoreCase("sedentary")) {
            baseSleepHours -= 0.5;
        } else if (activityLevel.equalsIgnoreCase("active")) {
            baseSleepHours += 0.5;
        } else if (activityLevel.equalsIgnoreCase("very active")) {
            baseSleepHours += 1.0;
        } else {
            throw new ApiException("Invalid activity level specified!");
        }

        // Adjust sleep hours based on stress level
        if (stressLevel.equalsIgnoreCase("Relaxed")) {
            baseSleepHours += 0.5;
        } else if (stressLevel.equalsIgnoreCase("Occasional")) {
            // No change for moderate stress
        } else if (stressLevel.equalsIgnoreCase("Frequent")) {
            baseSleepHours -= 1.0;
        } else if (stressLevel.equalsIgnoreCase("Chronic")) {
            baseSleepHours -= 1.5;
        } else {
            throw new ApiException("Invalid stress level specified!");
        }

        // Ensure sleep hours are within a reasonable range
        if (baseSleepHours < 5) {
            baseSleepHours = 5;
        } else if (baseSleepHours > 9) {
            baseSleepHours = 9;
        }

        hd.setSleepHours(baseSleepHours);
        healthDataRepository.save(hd);
        return hd;
    }


    public HealthData calculateWaterIntake(Integer userId, Integer id) {
        User user = userRepository.findUserByUserId(userId);
        if (user == null) {
            throw new ApiException("User id not found, please try again with a valid id");
        }
        HealthData hd = healthDataRepository.findHealthDataById(id);
        if (hd == null) {
            throw new ApiException("Health data id not found, please try again with a valid id");
        }
        double waterIntake;
        double baseIntake = hd.getWeight()*0.035; //Base Intake in liters
        String activityLevel = hd.getActivityLevel();

        if (activityLevel.equalsIgnoreCase("sedentary")) {
            waterIntake = baseIntake *1.1;
        }else if (activityLevel.equalsIgnoreCase("active")) {
            waterIntake= baseIntake *1.2;
        }else if (activityLevel.equalsIgnoreCase("very active")) {
            waterIntake=baseIntake *1.4;
        }else{
            throw new ApiException("Invalid activity level specified!");
        }
        hd.setWaterIntake(waterIntake);
        healthDataRepository.save(hd);
        return hd;
    }




}
