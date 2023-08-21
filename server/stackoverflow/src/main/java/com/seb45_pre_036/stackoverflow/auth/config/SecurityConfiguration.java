package com.seb45_pre_036.stackoverflow.auth.config;

import com.seb45_pre_036.stackoverflow.auth.filter.JwtAuthenticationFilter;
import com.seb45_pre_036.stackoverflow.auth.filter.JwtVerificationFilter;
import com.seb45_pre_036.stackoverflow.auth.handler.*;
import com.seb45_pre_036.stackoverflow.auth.jwt.JwtTokenizer;
import com.seb45_pre_036.stackoverflow.auth.utils.CustomAuthorityUtils;
import com.seb45_pre_036.stackoverflow.member.repository.MemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class SecurityConfiguration {

    private final JwtTokenizer jwtTokenizer;
    private final CustomAuthorityUtils customAuthorityUtils;
    private final MemberRepository memberRepository;

    public SecurityConfiguration(JwtTokenizer jwtTokenizer, CustomAuthorityUtils customAuthorityUtils, MemberRepository memberRepository) {
        this.jwtTokenizer = jwtTokenizer;
        this.customAuthorityUtils = customAuthorityUtils;
        this.memberRepository = memberRepository;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .headers().frameOptions().sameOrigin()
                .and()
                .csrf().disable()
                .cors(Customizer.withDefaults())
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin().disable()
                .httpBasic().disable()
                .exceptionHandling()
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                .accessDeniedHandler(new CustomAccessDeniedHandler())
                .and()
                .apply(new CustomFilterConfigurer())
                .and()
                .authorizeHttpRequests(authorize -> authorize
                        .antMatchers(HttpMethod.POST, "/members/signup").permitAll() // 회원가입
                        .antMatchers(HttpMethod.PATCH, "/members/**").hasRole("USER") // 마이페이지 수정
                        .antMatchers(HttpMethod.GET, "/members/myPage/**").hasRole("USER") // 마이페이지
                        .antMatchers(HttpMethod.DELETE, "/members/**").hasRole("USER") // 회원탈퇴
                        .antMatchers(HttpMethod.POST, "/questions/ask").hasRole("USER") // 질문 생성
                        .antMatchers(HttpMethod.PATCH, "/questions/**").hasRole("USER") // 질문 수정
                        .antMatchers(HttpMethod.DELETE, "/questions/**").hasRole("USER") // 질문 삭제
                        .antMatchers(HttpMethod.POST, "/answers").hasRole("USER") // 답변 생성
                        .antMatchers(HttpMethod.PATCH, "/answers/adopt/**").hasRole("USER") // 답변 채택
                        .antMatchers(HttpMethod.PATCH, "/answers/**").hasRole("USER") // 답변 수정
                        .antMatchers(HttpMethod.DELETE, "/answers/**").hasRole("USER") // 답변 삭제
                        .antMatchers(HttpMethod.POST, "/comments").hasRole("USER") // 댓글 생성
                        .antMatchers(HttpMethod.PATCH, "/comments/**").hasRole("USER") // 댓글 수정
                        .antMatchers(HttpMethod.DELETE, "/comments/**").hasRole("USER") // 댓글 삭제
                        .anyRequest().permitAll()
                );

        return http.build();

    }


    public class CustomFilterConfigurer extends AbstractHttpConfigurer<CustomFilterConfigurer, HttpSecurity>{
        @Override
        public void configure(HttpSecurity builder) throws Exception {
            AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);

            JwtAuthenticationFilter jwtAuthenticationFilter
                    = new JwtAuthenticationFilter(authenticationManager, jwtTokenizer);

            jwtAuthenticationFilter.setFilterProcessesUrl("/auth/login");
            jwtAuthenticationFilter.setAuthenticationSuccessHandler(new CustomAuthenticationSuccessHandler());
            jwtAuthenticationFilter.setAuthenticationFailureHandler(new CustomAuthenticationFailureHandler());

            JwtVerificationFilter jwtVerificationFilter
                    = new JwtVerificationFilter(jwtTokenizer, customAuthorityUtils);

            builder.addFilter(jwtAuthenticationFilter)
                    .addFilterAfter(jwtVerificationFilter, JwtAuthenticationFilter.class);

        }
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://localhost:8080"));

        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PATCH", "DELETE"));

        configuration.setAllowedHeaders(Arrays.asList("*"));

        configuration.setExposedHeaders(Arrays.asList("*"));

        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);


        return source;
    }

}
