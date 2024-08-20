package com.example.capstone2.Service;

import com.example.capstone2.Api.ApiException;
import com.example.capstone2.Model.Doctor;

import com.example.capstone2.Repsitory.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorService {
    private final DoctorRepository doctorRepository;


    // Get all the Users
    public List<Doctor> getAlldoctors() {
        return doctorRepository.findAll();
    }

    // Add new User
    public void addDoctor (Doctor doctor) {
        LocalDate birthDate = doctor.getDob();
        int age = Period.between(birthDate, LocalDate.now()).getYears();
        // Check if the user is at least 18 years old
        if (age <= 26) {
            throw new ApiException("Doctor must be at least 26 years old!");
        }
        doctor.setRegistrationDate(LocalDate.now());
        doctor.setUpdatedAt(LocalDate.now());
        doctorRepository.save(doctor);
    }

    // Update User
    public void updateDoctor (Integer doctorId,Doctor doctor) {
       Doctor doc = doctorRepository.findDoctorByDoctorId(doctorId);
        if (doc == null) {
            throw new ApiException("Doctor Id not found, please try again with a registered id!");
        }
        doc.setFirstName(doctor.getFirstName());
        doc.setLastName(doctor.getLastName());
        doc.setDob(doctor.getDob());
        doc.setGender(doctor.getGender());
        doc.setEmail(doctor.getEmail());
        doc.setPassword(doctor.getPassword());
        doc.setSpecialization(doctor.getSpecialization());
        doc.setLicenseNumber(doctor.getLicenseNumber());
        doc.setYearsOfExperience(doctor.getYearsOfExperience());
        doctorRepository.save(doc);
    }

    // Delete User
    public void deleteDoctor(Integer doctorId) {
        Doctor doc = doctorRepository.findDoctorByDoctorId(doctorId);
        if (doc == null) {
            throw new ApiException("Doctor Id not found, please try again with a registered id!");
        }
        doctorRepository.delete(doc);
    }


}
