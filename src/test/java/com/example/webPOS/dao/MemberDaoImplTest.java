package com.example.webPOS.dao;

import com.example.webPOS.dto.MemberDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
import jakarta.validation.Valid;

@SpringBootTest // 스프링 컨텍스트 등록
class MemberDaoImplTest {
    @Autowired
    private MemberDAO memberDAO;

    @Test
    void loginCheck() {
        String email = "han@google.com";//실제 있는데이터.
        MemberDTO rs = memberDAO.findByEmail(email);//쿼리 작동하는지 확인
        assertNotNull(rs);
    }

    @Test
    void save() {
    }

    @Test
    void showMember() {
    }

    @Test
    void findByEmail() {
    }
}