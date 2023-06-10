package com.example.webPOS.dto;


import java.time.LocalDateTime;

public class Member {
    private Long id;//유저 아이디
    private String password;//비밀번호
    private String name;//유저 이름
    private String email;//유저 이메일
    private String role;//유저 상태.
    private LocalDateTime datetime;//등록한 날짜 정보

    //생성자는 id 뺴고 만들었음. id가 primary key임
    public Member(String password, String name, String email, String role, LocalDateTime datetime) {
        this.password = password;
        this.name = name;
        this.email = email;
        this.role = role;
        this.datetime = datetime;
    }
    //LocalDateTime
    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return "MemberDTO{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", datetime=" + datetime +
                '}';
    }
}