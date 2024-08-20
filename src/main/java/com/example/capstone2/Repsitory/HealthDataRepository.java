package com.example.capstone2.Repsitory;

import com.example.capstone2.Model.HealthData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HealthDataRepository extends JpaRepository<HealthData,Integer> {

    HealthData findHealthDataById(Integer id);


    @Query("select hd from HealthData hd where hd.id=?1")
    HealthData calculateBMRByHealthDataId(Integer id);

}
