package com.example.webPOS.dao.interfaces;

//data access object 란 mysql db 에 접속해 데이터 추가, 삭제, 수정 작업하는클래스

import com.example.webPOS.dto.Member;

import java.util.List;

public interface MemberDAO {
    String loginCheck(Member dto);//로그인 체크했는지 파악하는 기능
    Member findByEmail(String email);//회원가입할때 이메일 중복 체크
    Member findById(Long id);
    void save(Member member);//회원가입 후 db에 저장 메소드
    List<Member> selectAll();//리스트로 화면단 뿌리기
    public int deleteById(Long id);
    public void update(Member member); //멤버 정보 업데이트 하기.

}
