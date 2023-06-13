package com.example.webPOS.service;

import com.example.webPOS.dao.interfaces.MemberDAO;
import com.example.webPOS.dto.Member;
import com.example.webPOS.dto.form.RegisterForm;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class MemberServiceTestForm {

    @Autowired
    private MemberDAO memberDao;

    @Test
    @Transactional //transactoinal 키워드는 db 연동하고, commit한것을 rollback 즉. 이 작업을 저장안한다는 뜻이다.
    public void testRegist() throws Exception {
        // Given
        RegisterForm registerForm = new RegisterForm();
        registerForm.setEmail("test2@example.com");
        registerForm.setPassword("password123");
        registerForm.setName("John Doe");
        registerForm.setRole("staff");

        MemberService memberService = new MemberService(memberDao);

        // When
        Long memberId = memberService.regist(registerForm);

        System.out.println(memberId);

        // Then
        Member savedMember = memberDao.findByEmail(registerForm.getEmail());
        //Assertions.assertNotNull(savedMember);
        Assertions.assertEquals(registerForm.getEmail(), savedMember.getEmail());
        Assertions.assertEquals(registerForm.getPassword(), savedMember.getPassword());
        Assertions.assertEquals(registerForm.getName(), savedMember.getName());
        Assertions.assertEquals(registerForm.getRole(), savedMember.getRole());
    }
}