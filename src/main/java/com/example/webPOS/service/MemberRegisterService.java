package com.example.webPOS.service;

import com.example.webPOS.dao.MemberDAO;
import com.example.webPOS.dto.MemberDTO;
import com.example.webPOS.dto.RegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


public class MemberRegisterService {

    private MemberDAO memberDao;

    @Autowired
    public MemberRegisterService(MemberDAO memberDao) {
        this.memberDao = memberDao;
    }

    public int regist(RegisterDTO req) throws Exception {
        MemberDTO member = memberDao.findByEmail(req.getEmail());
        if (member != null) {
            throw new Exception("DuplicateMemberException");
        }
        MemberDTO newMember = new MemberDTO(
                req.getEmail(),
                req.getPassword(),
                req.getName(),
                req.getRole(),
                LocalDateTime.now());
        memberDao.save(newMember);
        return newMember.getId();
    }
}
