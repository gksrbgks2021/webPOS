package com.example.webPOS.service;

import com.example.webPOS.dao.MemberDAO;
import com.example.webPOS.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public class MemberService {

    @Autowired
    private MemberDAO memberDAO;

    public MemberService(MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }

    public void registerMember(MemberDTO member) {
        //회원가입
        memberDAO.save(member);
    }

    public MemberDTO getMemberByEmail(String email) {
        // 주어진 email에 해당하는 회원 정보를 조회
        return memberDAO.findByEmail(email);
    }
}
