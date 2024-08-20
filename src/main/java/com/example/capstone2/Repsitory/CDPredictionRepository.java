package com.example.capstone2.Repsitory;

import com.example.capstone2.Model.CDPrediction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
//Chronic Disease Prediction
public interface CDPredictionRepository extends JpaRepository<CDPrediction, Integer> {

    @Query("select cd from CDPrediction cd where cd.id=?1")
    CDPrediction findCDPredictionById(Integer id);








}
