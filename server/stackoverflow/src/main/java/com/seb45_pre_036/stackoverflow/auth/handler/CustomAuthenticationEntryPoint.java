package com.seb45_pre_036.stackoverflow.auth.handler;

import com.seb45_pre_036.stackoverflow.auth.utils.ErrorResponder;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        if(request.getAttribute("ExpiredJwtException") != null){
            ErrorResponder.sendJwtErrorResponse(response, HttpStatus.UNAUTHORIZED);
        }
        else {
            ErrorResponder.sendErrorResponse(response, HttpStatus.UNAUTHORIZED);
        }
    }

}
