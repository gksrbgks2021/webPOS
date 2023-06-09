package com.example.webPOS.service;

import com.example.webPOS.dao.MemberDAO;
import com.example.webPOS.dto.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final MemberDAO memberDAO;

    @Autowired
    public LoginService(MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }

    public MemberDAO getMemberDAO() {
        return memberDAO;
    }

    public MemberDTO login(MemberDTO memberDTO){
        MemberDTO result = memberDAO.findByEmail(memberDTO.getEmail());
        return result;
    }

}