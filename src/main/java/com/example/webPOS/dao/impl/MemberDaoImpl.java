package com.example.webPOS.dao.impl;

import com.example.webPOS.dao.interfaces.MemberDAO;
import com.example.webPOS.dto.Member;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import java.sql.*;
import java.util.List;

public class MemberDaoImpl implements MemberDAO {
    private JdbcTemplate jdbcTemplate;

    //생성자로 bean 자동 주입
    @Autowired
    public MemberDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //db 접속해서 loginCheck 하는 기능입니다.
    @Override
    public String loginCheck(Member dto) {
        String query = "SELECT * FROM member WHERE email = ? AND password = ?";
        try {
            List<Member> members = jdbcTemplate.query(query,
                    (rs, rowNum) -> new Member(rs.getString("password"),
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
    public void save(Member member) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pstmt = con.prepareStatement(
                        "insert into MEMBER (EMAIL, PASSWORD, NAME,ROLE, REGDATE) values (?, ?, ?, ?,?)",
                        new String[]{"ID"}); //생성된 id의 기본 키 값 반환
                pstmt.setString(1, member.getEmail());
                pstmt.setString(2, member.getPassword());
                pstmt.setString(3, member.getName());
                pstmt.setString(4, member.getRole());
                pstmt.setTimestamp(5, Timestamp.valueOf(member.getDatetime()));
                return pstmt;
            }
        }, keyHolder);
        Number keyValue = keyHolder.getKey();
        member.setId(keyValue.longValue());//id 삽입
    }
    @Override
    public List<Member> selectAll() {
        List<Member> results = jdbcTemplate.query("select * from MEMBER", (ResultSet rs, int rowNum) -> {
            Member member = new Member(
                    rs.getString("PASSWORD"),
                    rs.getString("NAME"),
                    rs.getString("EMAIL"),
                    rs.getString("ROLE"),
                    rs.getTimestamp("REGDATE").toLocalDateTime());
            member.setId(rs.getLong("ID"));
            return member;
        });
        return results;
    }

    @Override
    public Member findByEmail(String email) {
        List<Member> results = jdbcTemplate.query("select * from MEMBER where EMAIL = ?", new RowMapper<Member>() {
            @Override
            public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
                Member member = new Member(rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("role"),
                        rs.getTimestamp("regdate").toLocalDateTime());
                member.setId(rs.getLong("ID"));
                return member;
            }
        }, email);

        return results.isEmpty() ? null : results.get(0);
    }
    @Override
    public Member findById(Long id){
//쿼리문
        List<Member> results = jdbcTemplate.query("select * from MEMBER where ID = ?", new RowMapper<Member>() {
            @Override
            public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
                Member member = new Member(rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("role"),
                        rs.getTimestamp("regdate").toLocalDateTime());
                member.setId(rs.getLong("ID"));
                return member;
            }
        }, id);
        return results.isEmpty() ? null : results.get(0);
    }

    @Override
    public int delete(Member member){
        String sql = "delete * from member where ID = ?";
        return jdbcTemplate.update(sql, member.getId());
    }
}
