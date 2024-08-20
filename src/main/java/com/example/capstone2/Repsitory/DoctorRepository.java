package com.example.capstone2.Repsitory;
import com.example.capstone2.Model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

    @Query("select d from Doctor d where d.doctorId=?1")
    Doctor findDoctorByDoctorId(Integer doctorId);
}
