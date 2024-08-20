package com.example.capstone2.Advise;


import com.example.capstone2.Api.ApiException;
import jakarta.validation.UnexpectedTypeException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class ControllerAdvise {

//When adding the Chronic Disease Prediction the doctor id was not available
    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity ApiException (ApiException e){
        String message = e.getMessage();

        return ResponseEntity.status(400).body(message);
    }


 //Duplicating a user email
    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity DataIntegrityViolationException (DataIntegrityViolationException e){
        String message = e.getMessage();

        return ResponseEntity.status(400).body(message);
    }

//Writing wrong words that not specified in the pattern
 //Writing a future date in the date of birth field
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity MethodArgumentNotValidException (MethodArgumentNotValidException e){
        String message = e.getMessage();
        return ResponseEntity.status(400).body(message);
    }


//Writing wrong Date of Birth JSON format
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity MessageNotReadableException (HttpMessageNotReadableException e){
        String message = e.getMessage();

        return ResponseEntity.status(400).body(message);
    }


//Forget to write the id in the path
    @ExceptionHandler(value = NoResourceFoundException.class)
    public ResponseEntity NoResourceFoundException (NoResourceFoundException e){
        String message = e.getMessage();

        return ResponseEntity.status(400).body(message);
    }

//Request method "Method 'GET' is not supported for adding a user.
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseEntity HttpRequestMethodNotSupportedException (HttpRequestMethodNotSupportedException e){
        String message = e.getMessage();

        return ResponseEntity.status(400).body(message);
    }


    //Writing the id as a letter instead of a number in the path
    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ResponseEntity MethodArgumentTypeMismatchException (MethodArgumentTypeMismatchException e){
        String message = e.getMessage();

        return ResponseEntity.status(400).body(message);
    }





}
