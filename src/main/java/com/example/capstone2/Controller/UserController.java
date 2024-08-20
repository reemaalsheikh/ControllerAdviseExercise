package com.example.capstone2.Controller;

import com.example.capstone2.Api.ApiResponse;
import com.example.capstone2.Model.User;
import com.example.capstone2.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/user")
public class UserController {
    private final UserService userService;


    // Get all the Users
    @GetMapping("/get")
    public ResponseEntity getAllUsers() {
        return ResponseEntity.status(200).body(userService.getAllUsers());
    }

    // Add new User
    @PostMapping("/add")
    public ResponseEntity addNewUser(@Valid @RequestBody User user) {
        userService.addUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("User Successfully added!"));
    }

    // Update User
    @PutMapping("/update/{userId}")
    public  ResponseEntity updateUser(@PathVariable Integer userId,@Valid @RequestBody User user){
        userService.updateUser(userId,user);
        return ResponseEntity.status(200).body(new ApiResponse("User Successfully updated!"));
    }

    // Delete User
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity deleteUser(@PathVariable Integer userId){
        userService.deleteUser(userId);
        return ResponseEntity.status(200).body(new ApiResponse("User Successfully deleted!"));
    }

    @GetMapping("/getAge/{userId}")
    public ResponseEntity calculateAgeByDOB (@PathVariable Integer userId){
        User age = userService.calculateAge(userId);
        return ResponseEntity.status(200).body(age);
    }


    @GetMapping("/gender/{gender}")
    public ResponseEntity findUserByGender(@PathVariable String gender){
        return ResponseEntity.status(200).body(userService.findUserByGender(gender));
    }

    @GetMapping("/insurance/{insurance}")
    public ResponseEntity getAllUsersWithInsurance (@PathVariable boolean insurance){
        return ResponseEntity.status(200).body(userService.getAllUsersWithInsurance(insurance));
    }


//    @GetMapping("/getAge/{birthDate}")
//    public ResponseEntity calculateAgeByDOB (@PathVariable LocalDate birthDate){
//        int age = userService.calculateAge(birthDate);
//        return ResponseEntity.status(200).body("User Age is:"+ age);
//    }

}
