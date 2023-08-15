package com.seb45_pre_036.stackoverflow.member.service;

import com.seb45_pre_036.stackoverflow.auth.jwt.JwtTokenizer;
import com.seb45_pre_036.stackoverflow.auth.utils.CustomAuthorityUtils;
import com.seb45_pre_036.stackoverflow.exception.BusinessLogicException;
import com.seb45_pre_036.stackoverflow.exception.ExceptionCode;
import com.seb45_pre_036.stackoverflow.member.entity.Member;
import com.seb45_pre_036.stackoverflow.member.repository.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final CustomAuthorityUtils customAuthorityUtils;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenizer jwtTokenizer;

    public MemberService(MemberRepository memberRepository,
                         CustomAuthorityUtils customAuthorityUtils,
                         PasswordEncoder passwordEncoder,
                         JwtTokenizer jwtTokenizer) {
        this.memberRepository = memberRepository;
        this.customAuthorityUtils = customAuthorityUtils;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenizer = jwtTokenizer;
    }


    // 검증된 회원 조회
    public Member findVerifiedMember(long memberId){
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
    public void checkMemberId(long memberId, String accessToken){

        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());
        long findMemberId = jwtTokenizer.getMemberIdFromAccessToken(accessToken, base64EncodedSecretKey);

        if(findMemberId != memberId){
            throw new BusinessLogicException(ExceptionCode.MEMBER_NOT_MATCHED);
        }
    }


    public Member createMember(Member member){
        verifyExistsEmail(member.getEmail());

        String encryptedPassword = passwordEncoder.encode(member.getPassword());
        member.setPassword(encryptedPassword);

        List<String> roles = customAuthorityUtils.createRoles(member.getEmail());

        member.setRoles(roles);

        return memberRepository.save(member);
    }

    public Member updateMember(Member member, String accessToken){

        Member findMember = findVerifiedMember(member.getMemberId());

        checkMemberId(findMember.getMemberId(), accessToken);

        Optional.ofNullable(member.getNickName())
                .ifPresent(nickName -> findMember.setNickName(nickName));

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
