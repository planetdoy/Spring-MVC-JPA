package com.doydoit.springmvcjpa.domain.login;

import com.doydoit.springmvcjpa.domain.member.Member;
import com.doydoit.springmvcjpa.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class LoginService {

    private final MemberRepository memberRepository;

    /**
     * 로그인
     */
    public Member login(String loginId, String password) {

        List<Member> members = memberRepository.findByLoginId(loginId);
        if (members == null) {
            return null;
        } else {
            for (Member member : members) {
                if (member.getPassword().equals(password)) {
                    return member;
                }
            }
        }
        return null;
    }
}
