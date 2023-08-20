package com.seb45_pre_036.stackoverflow.member.service;

import com.seb45_pre_036.stackoverflow.auth.jwt.JwtTokenizer;
import com.seb45_pre_036.stackoverflow.auth.utils.CustomAuthorityUtils;
import com.seb45_pre_036.stackoverflow.exception.BusinessLogicException;
import com.seb45_pre_036.stackoverflow.exception.ExceptionCode;
import com.seb45_pre_036.stackoverflow.member.entity.Member;
import com.seb45_pre_036.stackoverflow.member.repository.MemberRepository;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final CustomAuthorityUtils customAuthorityUtils;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenizer jwtTokenizer;


    public MemberService(MemberRepository memberRepository, CustomAuthorityUtils customAuthorityUtils,
                         PasswordEncoder passwordEncoder, JwtTokenizer jwtTokenizer) {
        this.memberRepository = memberRepository;
        this.customAuthorityUtils = customAuthorityUtils;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenizer = jwtTokenizer;
    }

    // 검증된 회원 조회
    private Member findVerifiedMember(long memberId){
        Optional<Member> optionalMember = memberRepository.findById(memberId);

        Member findMember = optionalMember
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        return findMember;
    }

    // 이미 등록된 회원인지 검증
    private void verifyExistsEmail(String email){
        Optional<Member> member = memberRepository.findByEmail(email);
        if(member.isPresent()){
            throw new BusinessLogicException(ExceptionCode.MEMBER_EXISTS);
        }

    }

    // 요청을 보낸 회원 -> 본인 검증
    private void checkMemberId(long memberId, String accessToken){

        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());
        long findMemberId = jwtTokenizer.getMemberIdFromAccessToken(accessToken, base64EncodedSecretKey);

        if(findMemberId != memberId){
            throw new BusinessLogicException(ExceptionCode.MEMBER_NOT_MATCHED);
        }
    }

    // refreshToken 검증 -> memberId 값 return
    // refreshToken 만료 / signature 예외 / 그외 예외 발생 -> GlobalExceptionHandler 통해 -> 예외 정보 제공
    private long verifyRefreshToken(String refreshToken, String base64EncodedSecretKey){

        long findMemberId = jwtTokenizer.getMemberIdFromRefreshToken(refreshToken, base64EncodedSecretKey);

        return findMemberId;
    }



    public Member createMember(Member member){
        verifyExistsEmail(member.getEmail());

        String encryptedPassword = passwordEncoder.encode(member.getPassword());
        member.setPassword(encryptedPassword);

        List<String> roles = customAuthorityUtils.createRoles(member.getEmail());

        member.setRoles(roles);

        return memberRepository.save(member);
    }


    // refreshToken 검증 -> accessToken 생성 후 return
    public String renewAccessToken(String refreshToken){
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());
        // base64 인코딩 SecretKey 생성

        long findMemberId = verifyRefreshToken(refreshToken, base64EncodedSecretKey);
        // refreshToken 검증 -> refreshToken 담긴 memberId 값을 가져옴.

        Member findMember = findVerifiedMember(findMemberId);
        // memberId 값 가진 회원이 있는지 조회
        // -> 조회 후 해당 회원 정보를 가져옴.

        // 조회한 회원 정보 -> accessToken 필요한 정보 생성
        // -> 해당 정보 활용 -> accessToken 발행 -> return
        Map<String, Object> claims = new HashMap<>();
        claims.put("memberId", findMember.getMemberId());
        claims.put("email", findMember.getEmail());
        claims.put("roles", findMember.getRoles());

        String subject = findMember.getEmail();
        Date expiration = jwtTokenizer.getTokenExpiration(jwtTokenizer.getAccessTokenExpirationMinutes());

        String accessToken = "Bearer "
                + jwtTokenizer.generateAccessToken(claims, subject, expiration, base64EncodedSecretKey);

        return accessToken;
    }


    public Member updateMember(Member member, String accessToken){

        Member findMember = findVerifiedMember(member.getMemberId());

        checkMemberId(findMember.getMemberId(), accessToken);

        Optional.ofNullable(member.getNickName())
                .ifPresent(nickName -> findMember.setNickName(nickName));
        Optional.ofNullable(member.getTitle())
                .ifPresent(title -> findMember.setTitle(title));
        Optional.ofNullable(member.getAboutMe())
                .ifPresent(aboutMe -> findMember.setAboutMe(aboutMe));


        return memberRepository.save(findMember);
    }

    public Member findMember(long memberId, String accessToken){

        Member findMember = findVerifiedMember(memberId);

        checkMemberId(findMember.getMemberId(), accessToken); // memberId 체크

        return findMember;
    }


    public Page<Member> findMembers(int page, int size){

        return memberRepository.findAll(
                PageRequest.of(page, size, Sort.by("memberId").descending()));

    }

    public void deleteMember(long memberId, String accessToken){

        Member findMember = findVerifiedMember(memberId);

        checkMemberId(findMember.getMemberId(), accessToken);

        memberRepository.delete(findMember);
    }


}
