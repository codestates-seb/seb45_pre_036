package com.seb45_pre_036.stackoverflow.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class ErrorResponse {

    private int status;
    private String message;
    private List<FieldError> fieldErrors;
    private List<ConstraintViolationError> constraintViolationErrors;

    public void setStatusAndMessageFromExceptionCode(ExceptionCode exceptionCode){
        this.status = exceptionCode.getStatus();
        this.message = exceptionCode.getMessage();
    }

    public void setStatusAndMessageFromHttpStatus(HttpStatus httpStatus){
        this.status = httpStatus.value();
        this.message = httpStatus.getReasonPhrase();
    }

    public void setStatusAndMessageFromHttpStatusAndMessage(HttpStatus httpStatus, String message){
        this.status = httpStatus.value();
        this.message = message;
    }


    public void setFieldErrors(BindingResult bindingResult){

        this.fieldErrors = bindingResult.getFieldErrors()
                .stream().map(error -> new FieldError(
                        error.getField(),
                        error.getRejectedValue() == null ? "" : error.getRejectedValue().toString(),
                        error.getDefaultMessage()
                )).collect(Collectors.toList());
    }

    public void setConstraintViolationErrors(Set<ConstraintViolation<?>> constraintViolations){
        this.constraintViolationErrors = constraintViolations.stream()
                .map(constraintViolation -> new ConstraintViolationError(
                        constraintViolation.getPropertyPath().toString(),
                        constraintViolation.getInvalidValue().toString(),
                        constraintViolation.getMessage()
                )).collect(Collectors.toList());
    }


    @Getter
    @AllArgsConstructor
    public static class FieldError{
        private String field;
        private Object rejectedValue;
        private String reason;
    }

    @Getter
    @AllArgsConstructor
    public static class ConstraintViolationError{
        private String propertyPath;
        private Object rejectedValue;
        private String reason;
    }


}
