package com.kdomo.Myhome.service;

import com.kdomo.Myhome.model.Member;
import com.kdomo.Myhome.model.Role;
import com.kdomo.Myhome.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    public Member save(Member member){
        String encodedPassword = passwordEncoder.encode(member.getPassword());
        member.setPassword(encodedPassword);
        member.setEnabled("T");
        Role role = new Role();
        role.setId(1l);
        member.getRoles().add(role);
        return memberRepository.save(member);
    }
}
