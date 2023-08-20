package com.seb45_pre_036.stackoverflow.auth.utils;

import com.google.gson.Gson;
import com.seb45_pre_036.stackoverflow.exception.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ErrorResponder {
    public static void sendErrorResponse(HttpServletResponse response, HttpStatus httpStatus) throws IOException{

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatusAndMessageFromHttpStatus(httpStatus);

        Gson gson = new Gson();

        response.setStatus(httpStatus.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(gson.toJson(errorResponse, ErrorResponse.class));

    }

    public static void sendJwtErrorResponse(HttpServletResponse response, HttpStatus httpStatus) throws IOException{

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatusAndMessageFromHttpStatusAndMessage(
                httpStatus, "AccessToken expired");

        Gson gson = new Gson();

        response.setStatus(httpStatus.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(gson.toJson(errorResponse, ErrorResponse.class));

    }
}
