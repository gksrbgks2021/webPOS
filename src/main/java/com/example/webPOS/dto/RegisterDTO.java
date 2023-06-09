package com.example.webPOS.dto;

//회원가입 양식
public class RegisterDTO {
    private String email;
    private String password;
    private String confirmPassword;
    private String name;
    private String role;

    public RegisterDTO(String email, String password, String confirmPassword, String name, String role) {
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.name = name;
        this.role = role;
    }

    public String getRole() {return role;}

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPasswordEqualToConfirmPassword() {
        return password.equals(confirmPassword);
    }
}
