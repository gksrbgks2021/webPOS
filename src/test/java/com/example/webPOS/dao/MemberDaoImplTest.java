package com.example.webPOS.dao;

import com.example.webPOS.dto.MemberDTO;
import com.example.webPOS.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
import jakarta.validation.Valid;

import java.util.List;

@SpringBootTest // 스프링 컨텍스트 등록
class MemberDaoImplTest {
    @Autowired
    private MemberDAO memberDAO;

    @Test
    void loginCheck() {
        String email = "han@google.com";//실제 있는데이터.
        MemberDTO rs = memberDAO.findByEmail(email);//쿼리 작동하는지 확인
        assertEquals(rs.getEmail(), email);//요청한 이메일과 받아온 이메일이 동일한지 확인
        System.out.println(rs.getEmail());
    }
    @Test
    void save() {

    }

    @Test
    void 아이디찾기테스트(){
        long id = 1;
        MemberDTO rs = memberDAO.findById(id);

        assertEquals(id,rs.getId());
    }
    @Test
    void 테이블전체조회() {
        List<MemberDTO> rs = memberDAO.selectAll();
        for(MemberDTO m : rs)
            System.out.println(m.toString());
    }

    @Test
    void findByEmail() {
    }
}