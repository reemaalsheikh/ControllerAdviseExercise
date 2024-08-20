package com.example.capstone2.Service;

import com.example.capstone2.Api.ApiException;
import com.example.capstone2.Model.Appointments;
import com.example.capstone2.Model.Doctor;
import com.example.capstone2.Model.User;
import com.example.capstone2.Repsitory.AppointmentsRepository;
import com.example.capstone2.Repsitory.DoctorRepository;
import com.example.capstone2.Repsitory.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentsService {
    private final AppointmentsRepository appointmentsRepository;
    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;

    //Get all appointments
    public List<Appointments> getAllAppointments() {
        return appointmentsRepository.findAll();
    }

    //Add new Appointment
    public void addAppointment(Appointments appointments) {
        User user = userRepository.findUserByUserId(appointments.getUserId());
        if (user == null) {
            throw new ApiException("User id not found");
        }
        Doctor doctor = doctorRepository.findDoctorByDoctorId(appointments.getDoctorId());
        if (doctor == null) {
            throw new ApiException("Doctor id not found");
        }
        appointments.setCreatedAt(LocalDate.now());
        appointments.setUpdatedAt(LocalDate.now());
        appointmentsRepository.save(appointments);
    }

    // Update Appointments
    public void updateAppointment(Appointments appointments, Integer id) {
        Appointments a = appointmentsRepository.findAppointmentsById(id);
        if (a == null) {
            throw new ApiException("Appointment id not found!");
        }
        a.setName(appointments.getName());
        a.setAppointmentDate(LocalDate.now());
        a.setVisitPurpose(appointments.getVisitPurpose());
        a.setUpdatedAt(LocalDate.now());
        appointmentsRepository.save(a);
    }


    //Delete Appointments
    public void deleteAppointment(Integer id) {
        Appointments a = appointmentsRepository.findAppointmentsById(id);
        if (a == null) {
            throw new ApiException("Appointment id not found!");
        }
        appointmentsRepository.delete(a);
    }


    public double AppointmentFee (Integer userId, Integer id) {
        double fee=200;

        Appointments a = appointmentsRepository.findAppointmentsById(id);
        if (a == null) {
            throw new ApiException("Appointment id not found!");
        }
        User user = userRepository.findUserByUserId(userId);
        if (user == null) {
            throw new ApiException("User id not found, please try again with a valid id");
        }

        double finalFee=fee;
    if (user.isHasInsurance()){
        String insuranceClass = user.getInsuranceClass();
        if(insuranceClass.equalsIgnoreCase("A")){
            finalFee=0.0;
        }else if(insuranceClass.equalsIgnoreCase("B")){
            finalFee= fee *0.20;
        }else if(insuranceClass.equalsIgnoreCase("C")){
            finalFee= fee *0.30;
        }else {
            throw new ApiException("Invalid insurance class specified!");
        }
    }
    a.setAppointmentFee(finalFee);
    appointmentsRepository.save(a);
    return finalFee;
    }


    //get all appointment for a specific doctor
    public List<Appointments> getAppointmentsByDoctorId(Integer doctorId) {
        List<Appointments> a = appointmentsRepository.findAppointmentsByDoctorId(doctorId);

        if(a.isEmpty()){
            throw new ApiException("Doctor id not found");
        }

        return a;

    }



}
