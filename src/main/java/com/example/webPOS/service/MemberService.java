package com.example.webPOS.service;

import com.example.webPOS.dao.interfaces.MemberDAO;
import com.example.webPOS.dto.Member;
import com.example.webPOS.dto.form.RegisterForm;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

//config에 수동 빈 등록함.
public class MemberService {

    private MemberDAO memberDao;

    @Autowired
    public MemberService(MemberDAO memberDao) {
        this.memberDao = memberDao;
    }

    //회원 등록
    public Long regist(RegisterForm registerForm) throws Exception {
        Member member = memberDao.findByEmail(registerForm.getEmail());
        if (member != null) {
            throw new Exception("DuplicateMemberException");
        }
        Member newMember = new Member(
                registerForm.getPassword(),
                registerForm.getName(),
                registerForm.getEmail(),
                registerForm.getRole(),
                LocalDateTime.now());
        memberDao.save(newMember);
        return newMember.getId();
    }
    // 회원 삭제
    public int deleteByEmail(String email) throws Exception {
        Member member = memberDao.findByEmail(email);
        if (member == null) {
            throw new Exception("MemberNotFoundException");
        }
        return memberDao.deleteById(member.getId());
    }

    //회원 수정
    public void updateMember(long id)throws  Exception{
        Member member = memberDao.findById(id);
        try{
            memberDao.update(member);
        }catch (Exception e){
            throw new Exception("update 에 실패하였습니다");
        }
    }

    public int deleteById(Long id) throws Exception {
        int ret = 0;
        try{
            ret = memberDao.deleteById(id);
        }catch (Exception e){
            throw new Exception("삭제실패했음");
        }
        return ret;
    }
    public List<Member> showMember(){
        return memberDao.selectAll();
    }
}
