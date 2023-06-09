package com.example.webPOS.service;

import com.example.webPOS.PosApplication;
import com.example.webPOS.dao.MemberDAO;
import com.example.webPOS.dto.MemberDTO;
import com.example.webPOS.dto.RegisterDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MemberRegisterServiceTest {

    @Autowired
    private MemberDAO memberDao;

    @Test
    @Transactional
    public void testRegist() throws Exception {
        // Given
        RegisterDTO registerDTO = new RegisterDTO();
        registerDTO.setEmail("test@example.com");
        registerDTO.setPassword("password123");
        registerDTO.setName("John Doe");
        registerDTO.setRole("staff");

        MemberRegisterService memberService = new MemberRegisterService(memberDao);

        // When
        Long memberId = memberService.regist(registerDTO);


        // Then
        MemberDTO savedMember = memberDao.findByEmail(registerDTO.getEmail());
        //Assertions.assertNotNull(savedMember);
        Assertions.assertEquals(registerDTO.getEmail(), savedMember.getEmail());
        Assertions.assertEquals(registerDTO.getPassword(), savedMember.getPassword());
        Assertions.assertEquals(registerDTO.getName(), savedMember.getName());
        Assertions.assertEquals(registerDTO.getRole(), savedMember.getRole());
    }


}