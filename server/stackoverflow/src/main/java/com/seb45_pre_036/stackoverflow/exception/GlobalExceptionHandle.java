package com.seb45_pre_036.stackoverflow.exception;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandle {

    @ExceptionHandler
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException e){

        ErrorResponse response = new ErrorResponse();
        response.setFieldErrors(e.getBindingResult());

        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler
    public ResponseEntity handleConstraintViolationException(ConstraintViolationException e){

        ErrorResponse response = new ErrorResponse();
        response.setConstraintViolationErrors(e.getConstraintViolations());

        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity handleBusinessLogicException(BusinessLogicException e){

        ErrorResponse response = new ErrorResponse();
        response.setStatusAndMessageFromExceptionCode(e.getExceptionCode());

        return new ResponseEntity(response, HttpStatus.valueOf(e.getExceptionCode().getStatus()));
    }


    // refreshToken 유효 시간 만료 예외 처리
    @ExceptionHandler
    public ResponseEntity handleExpiredJwtException(ExpiredJwtException e){

        ErrorResponse response = new ErrorResponse();
        response.setStatusAndMessageFromHttpStatusAndMessage(HttpStatus.UNAUTHORIZED, "RefreshToken expired");

        return new ResponseEntity(response, HttpStatus.UNAUTHORIZED);
    }

    // refreshToken signature 예외 처리
    @ExceptionHandler
    public ResponseEntity handleSignatureException(SignatureException e){

        ErrorResponse response = new ErrorResponse();
        response.setStatusAndMessageFromHttpStatus(HttpStatus.UNAUTHORIZED);

        return new ResponseEntity(response, HttpStatus.UNAUTHORIZED);
    }


    @ExceptionHandler
    public ResponseEntity handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e){

        ErrorResponse response = new ErrorResponse();
        response.setStatusAndMessageFromHttpStatus(HttpStatus.METHOD_NOT_ALLOWED);

        return new ResponseEntity(response, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler
    public ResponseEntity handleHttpMessageNotReadableException(HttpMessageNotReadableException e){

        ErrorResponse response = new ErrorResponse();
        response.setStatusAndMessageFromHttpStatusAndMessage(
                HttpStatus.BAD_REQUEST, "Required request body is missing");

        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity handleMissingServletRequestParameterException(MissingServletRequestParameterException e){

        ErrorResponse response = new ErrorResponse();
        response.setStatusAndMessageFromHttpStatusAndMessage(HttpStatus.BAD_REQUEST, e.getMessage());

        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity handleException(Exception e){

        ErrorResponse response = new ErrorResponse();
        response.setStatusAndMessageFromHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
