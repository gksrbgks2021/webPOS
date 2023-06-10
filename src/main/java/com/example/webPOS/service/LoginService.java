package com.example.webPOS.service;

import com.example.webPOS.dao.interfaces.MemberDAO;
import com.example.webPOS.dto.Member;
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

    /**
     *
     * @return null is login failed
     */
    public Member login(String email, String pw){
        Member result =  memberDAO.findByEmail(email);
        return result.getPassword().equals(pw) ? result : null;
    }

}
