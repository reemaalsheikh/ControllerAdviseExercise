package com.example.capstone2.Repsitory;

import com.example.capstone2.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserByUserId(Integer userId);

    @Query("select u from User u where u.role=?1")
    User findUserByRole(String role);

    @Query("select u from User u where u.userId=?1")
    User calculateAgeByUserId(Integer userId);

    @Query("select u from User u where u.gender=?1")
    List<User> findUserByGender(String gender);

   @Query("select u from User u where u.hasInsurance=true")
    List<User> GetAllUsersWhoHasInsurance(boolean hasInsurance);


//    @Query("select u from User u where u.dateOfBirth=?1")
//    User calculateAgeByDateOfBirth(LocalDate dateOfBirth);

}
