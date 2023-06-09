package com.example.webPOS.dao;

import com.example.webPOS.dto.MemberDTO;
import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MemberDaoImpl implements MemberDAO{
    private JdbcTemplate jdbcTemplate;

    //생성자로 bean 자동 주입
    @Autowired
    public MemberDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //db 접속해서 loginCheck 하는 기능입니다.
    @Override
    public String loginCheck(MemberDTO dto) {
        String query = "SELECT * FROM member WHERE email = ? AND password = ?";
        try {
            List<MemberDTO> members = jdbcTemplate.query(query,
                    (rs, rowNum) -> new MemberDTO(rs.getString("password"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("role"),
                            rs.getTimestamp("datetime").toLocalDateTime()
                    ),
                    dto.getEmail(),
                    dto.getPassword());

            if (!members.isEmpty()) {
                // 로그인 성공하면 success 리턴
                return "success";
            } else {
                // 로그인 실패
                return "failure";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "failure";
        }
    }

    @Override
    public void save(MemberDTO member) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pstmt = con.prepareStatement(
                        "insert into MEMBER (EMAIL, PASSWORD, NAME,ROLE, REGDATE) values (?, ?, ?, ?,?)",
                        new String[] { "ID" });
                pstmt.setString(1, member.getEmail());
                pstmt.setString(2, member.getPassword());
                pstmt.setString(3, member.getName());
                pstmt.setString(4, member.getRole());
                pstmt.setTimestamp(5, Timestamp.valueOf(member.getDatetime()));
                return pstmt;
            }
        }, keyHolder);
        Number keyValue = keyHolder.getKey();
        member.setId(keyValue.intValue());
    }

    @Override
    public ArrayList<MemberDTO> showMember() {
        return null;
    }

    @Override
    public MemberDTO findByEmail(String email) {
        //쿼리문
        String query = "SELECT * FROM MEMBER WHERE email = ?";
        //실행한 쿼리문을 리스트로 받아온다.
        List<MemberDTO> results = jdbcTemplate.query(query,
                (rs, rowNum) -> new MemberDTO(rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("role"),
                        rs.getTimestamp("regdate").toLocalDateTime()),
                email);
        //리스트 길이가 0 이면 null 반환
        return results.isEmpty() ? null : results.get(0);
    }
}
