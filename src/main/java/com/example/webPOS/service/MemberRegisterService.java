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

    //회원 등록
    public Long regist(RegisterDTO registerDTO) throws Exception {
        MemberDTO member = memberDao.findByEmail(registerDTO.getEmail());
        if (member != null) {
            throw new Exception("DuplicateMemberException");
        }
        MemberDTO newMember = new MemberDTO(
                registerDTO.getPassword(),
                registerDTO.getName(),
                registerDTO.getEmail(),
                registerDTO.getRole(),
                LocalDateTime.now());
        memberDao.save(newMember);
        return newMember.getId();
    }
}
