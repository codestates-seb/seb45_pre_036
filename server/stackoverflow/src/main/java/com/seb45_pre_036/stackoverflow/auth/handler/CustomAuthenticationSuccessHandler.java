package com.seb45_pre_036.stackoverflow.auth.handler;

import com.google.gson.Gson;
import com.seb45_pre_036.stackoverflow.member.entity.Member;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        Member member = (Member) authentication.getPrincipal();
        sendSuccessResponse(response, member);

    }

    private void sendSuccessResponse(HttpServletResponse response, Member member) throws IOException{
        Gson gson = new Gson();
        LoginUserInfo loginUserInfo = new LoginUserInfo();
        loginUserInfo.setMemberId(member.getMemberId());
        loginUserInfo.setEmail(member.getEmail());

        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(gson.toJson(loginUserInfo, LoginUserInfo.class));

    }

    @Getter
    @Setter
    private class LoginUserInfo{
        private long memberId;
        private String email;
    }
}
