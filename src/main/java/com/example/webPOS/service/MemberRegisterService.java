package com.example.webPOS.service;

import com.example.webPOS.dao.interfaces.MemberDAO;
import com.example.webPOS.dto.Member;
import com.example.webPOS.dto.form.RegisterForm;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

//config에 수동 빈 등록함.
public class MemberRegisterService {

    private MemberDAO memberDao;

    @Autowired
    public MemberRegisterService(MemberDAO memberDao) {
        this.memberDao = memberDao;
    }

    //회원 등록
    public Long regist(RegisterForm registerForm) throws Exception {
        Member member = memberDao.findByEmail(registerForm.getEmail());
        if (member != null) {
            throw new Exception("DuplicateMemberException");
        }
        Member newMember = new Member(
                registerForm.getPassword(),
                registerForm.getName(),
                registerForm.getEmail(),
                registerForm.getRole(),
                LocalDateTime.now());
        memberDao.save(newMember);
        return newMember.getId();
    }
    // 회원 삭제
    public int deleteByEmail(String email) throws Exception {
        Member member = memberDao.findByEmail(email);
        if (member == null) {
            throw new Exception("MemberNotFoundException");
        }
        return memberDao.delete(member);
    }
}
