package com.seb45_pre_036.stackoverflow.auth.handler;

import com.seb45_pre_036.stackoverflow.auth.jwt.JwtTokenizer;
import com.seb45_pre_036.stackoverflow.auth.utils.CustomAuthorityUtils;
import com.seb45_pre_036.stackoverflow.exception.BusinessLogicException;
import com.seb45_pre_036.stackoverflow.exception.ExceptionCode;
import com.seb45_pre_036.stackoverflow.member.entity.Member;
import com.seb45_pre_036.stackoverflow.member.repository.MemberRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.*;

public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtTokenizer jwtTokenizer;
    private final CustomAuthorityUtils customAuthorityUtils;
    private final MemberRepository memberRepository;


    public OAuth2LoginSuccessHandler(JwtTokenizer jwtTokenizer,
                                     CustomAuthorityUtils customAuthorityUtils,
                                     MemberRepository memberRepository) {
        this.jwtTokenizer = jwtTokenizer;
        this.customAuthorityUtils = customAuthorityUtils;
        this.memberRepository = memberRepository;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        FilterChain chain,
                                        Authentication authentication) throws IOException, ServletException {

        var oAuth2User = (OAuth2User) authentication.getPrincipal();

        String email = String.valueOf(oAuth2User.getAttributes().get("email"));
        String nickName = String.valueOf(oAuth2User.getAttributes().get("name"));

        Member member = saveMemberToDB(email, nickName);


        setTokenToResponseHeader(request, response, member);
        // 리다이렉트 하지 않는 경우
        // response header -> 토큰 정보를 담아서 -> 클라이언트 측 제공

        redirect(request, response, member);
        // 리다이렉트 하는 경우
        // 리다이렉트 url -> 쿼리파라미터 -> 토큰 포함
        // -> 해당 url -> 리다이렉트 -> 클라이언트 측 -> 토큰 제공


    }

    private Member saveMemberToDB(String email, String nickName){

        Optional<Member> optionalMember = memberRepository.findByEmail(email);
        Member findMember = optionalMember.orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_EXISTS));
        // OAuth2 로그인 -> email -> 서버 측 이미 가입된 회원인지 검증
        // -> 이미 가입된 회원일 경우 -> Exception 발생

        List<String> authorities = customAuthorityUtils.createRoles(email);

        Member member = new Member();
        member.setEmail(email);
        member.setNickName(nickName);
        member.setRoles(authorities);

        return member;

    }

    private void setTokenToResponseHeader(HttpServletRequest request,
                             HttpServletResponse response,
                             Member member) throws IOException{

        // -> 리다이렉트 수행하지 않을 경우
        // -> response header -> 토큰 정보를 담아서 -> 클라이언트 측 제공

        String accessToken =  "Bearer " + delegateAccessToken(member);
        String refreshToken = delegateRefreshToken(member);

        response.setHeader("Authorization", accessToken);
        response.setHeader("Refresh", refreshToken);

    }


    private void redirect(HttpServletRequest request,
                          HttpServletResponse response,
                          Member member) throws IOException{

        String accessToken =  "Bearer " + delegateAccessToken(member);
        String refreshToken = delegateRefreshToken(member);

        // -> 클라이언트 측 -> 쿼리파라미터 형식으로 토큰을 전달 받을 -> url 존재할 경우
        // -> 해당 url -> 쿼리 파라미터 형식 -> 토큰 포함
        // -> 해당 url -> 리다이렉트 -> 해당 토큰 -> 클라이언트 측 제공

        String uri = createURI(accessToken, refreshToken).toString();
        // 클라이언트 측 -> 토큰 전달하기 위해 -> 쿼리 파라미터 형식 -> 토큰 설정
        // -> 리다이렉트 uri 생성

        getRedirectStrategy().sendRedirect(request, response, uri);
        // -> 쿼리 파라미터 형식으로 포함된 토큰
        // -> 리다이렉트 uri -> 리다이렉트 -> 클라이언트 측에게 토큰을 제공

    }


    // AccessToken 생성 -> RETURN
    private String delegateAccessToken(Member member){
        Map<String, Object> claims = new HashMap<>();
        claims.put("memberId", member.getMemberId());
        claims.put("username", member.getEmail());
        claims.put("roles", member.getRoles());

        String subject = member.getEmail();
        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getAccessTokenExpirationMinutes());

        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());

        String accessToken = jwtTokenizer.generateAccessToken(claims, subject, expiration, base64EncodedSecretKey);

        return accessToken;
    }

    // RefreshToken 생성 -> return
    private String delegateRefreshToken(Member member){
        Map<String, Object> claims = new HashMap<>();
        claims.put("memberId", member.getMemberId());

        String subject = member.getEmail();
        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getRefreshTokenExpirationMinutes());
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());

        String refreshToken = jwtTokenizer.generateRefreshToken(claims, subject, expiration, base64EncodedSecretKey);

        return refreshToken;
    }

    // 토큰 -> 쿼리파라미터 -> 리다이렉트 URI 생성
    private URI createURI(String accessToken, String refreshToken){
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("access_token", accessToken);
        queryParams.add("refresh_token", refreshToken);

        return UriComponentsBuilder
                .newInstance()
                .scheme("http")
                .host("localhost")
                .port(80)
                .path("/receive-token.html")
                // 클라이언트 측 -> 쿼리 파라미터 형식으로 토큰을 전달받을 -> url
                // 해당 부분 -> accessToken / refreshToken -> 쿼리 파라미터 포함시켜
                // 클라이언트 측으로 전달할, 리다이렉트 할 -> url 설정
                // -> 클라이언트 측 -> 파라미터 형식으로 토큰을 전달받을 -> url -> 설정
                .queryParams(queryParams) // 쿼리 파라미터 -> 토큰 추가
                .build()
                .toUri();
    }
}
