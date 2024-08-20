package com.example.capstone2.Repsitory;

import com.example.capstone2.Model.Recommendations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecommendationsRepository extends JpaRepository<Recommendations, Integer> {

    Recommendations findRecommendationsById(int id);
}
