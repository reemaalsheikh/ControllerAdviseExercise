package com.example.capstone2.Repsitory;

import com.example.capstone2.Model.Appointments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentsRepository extends JpaRepository<Appointments, Integer> {

    @Query("select a from Appointments a where a.id=?1")
    Appointments findAppointmentsById(Integer id);


    @Query("select a from Appointments a where a.doctorId=?1")
    List<Appointments> findAppointmentsByDoctorId(Integer doctorId);



}
