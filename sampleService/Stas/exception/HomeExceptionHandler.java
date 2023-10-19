package com.sysco.sampleService.Stas.exception;

import com.sysco.sampleService.Stas.errors.HomeError;
import com.sysco.sampleService.Stas.validation.SellerIDValidation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HomeExceptionHandler {

    @ExceptionHandler(value = DBConnectionException.class)
    public ResponseEntity<HomeError> handleException(DBConnectionException dbe){
        HomeError homeError = new HomeError(HttpStatus.INTERNAL_SERVER_ERROR.value(), dbe.getMessage());
        return new ResponseEntity<>(homeError, HttpStatus.valueOf(homeError.getCode()));
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<HomeError> handleValidation(SellerIDValidation sellerIDValidation){
        HomeError homeError = new HomeError(HttpStatus.INTERNAL_SERVER_ERROR.value(), sellerIDValidation.message());
        return new ResponseEntity<>(homeError, HttpStatusCode.valueOf(homeError.getCode()));
    }
}
