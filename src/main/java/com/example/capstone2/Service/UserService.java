package com.example.capstone2.Service;

import com.example.capstone2.Api.ApiException;
import com.example.capstone2.Model.User;
import com.example.capstone2.Repsitory.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // Get all the Users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Add new User
    public void addUser (User user) {
        LocalDate birthDate = user.getDateOfBirth();
        int age = Period.between(birthDate, LocalDate.now()).getYears();
        // Check if the user is at least 18 years old
        if (age < 18) {
            throw new ApiException("User must be at least 18 years old!");
        }
        user.setRegistrationDate(LocalDate.now());
        user.setUpdatedAt(LocalDate.now());
        userRepository.save(user);
    }

    // Update User
    public void updateUser (Integer userId,User user) {
        User u = userRepository.findUserByUserId(userId);
        if (u == null) {
            throw new ApiException("User Id not found, please try again with a registered id!");
        }
//        User uAge= userRepository.calculateAgeByUserId(userId);
        u.setFullName(user.getFullName());
        u.setAge(user.getAge());
//        u.setAge(uAge.getAge());
        u.setDateOfBirth(user.getDateOfBirth());
        u.setGender(user.getGender());
        u.setRole(user.getRole());
        u.setEmail(user.getEmail());
        u.setPassword(user.getPassword());
        user.setUpdatedAt(LocalDate.now());
        userRepository.save(u);
    }

    // Delete User
    public void deleteUser(Integer userId) {
        User u = userRepository.findUserByUserId(userId);
        if (u == null) {
            throw new ApiException("User Id not found, please try again with a registered id!");
        }
        userRepository.delete(u);
    }

    // Method to calculate age by user ID

    public User calculateAge(Integer userId) {
       // User user = userRepository.findUserByUserId(userId);
        User user = userRepository.calculateAgeByUserId(userId);
        if (user == null) {
            throw new ApiException("User id not found!");
        }
        LocalDate birthDate = user.getDateOfBirth();
        if (birthDate == null) {
            throw new ApiException("Date of birth not provided!");
        }

       int age = Period.between(birthDate, LocalDate.now()).getYears();
        // Check if the user is at least 18 years old
        if (age < 18) {
            throw new ApiException("User must be at least 18 years old!");
        }
        user.setAge(age);
        userRepository.save(user);
        return user;
    }



public List<User> findUserByGender(String gender) {
        List<User> users = userRepository.findUserByGender(gender);
        if (users.isEmpty()) {
            throw new ApiException("User not found, please try again with a registered gender!");
        }
        return users;
}


public List<User> getAllUsersWithInsurance (boolean hasInsurance) {
   List<User> users = userRepository.GetAllUsersWhoHasInsurance(hasInsurance);
   if (users.isEmpty()) {
       throw new ApiException("No user with insurance found!");
   }
   return users;
}


//    public int calculateAge(LocalDate dateOfBirth) {
//        User user = userRepository.calculateAgeByDateOfBirth(dateOfBirth);
//        if (user == null) {
//            throw new ApiException("Date of birth not found!");
//        }
//        int age = Period.between(dateOfBirth, LocalDate.now()).getYears();
//        // Check if the user is at least 18 years old
//        if (age < 18) {
//            throw new ApiException("User must be at least 18 years old!");
//        }
//
//        return age;
//    }






}
