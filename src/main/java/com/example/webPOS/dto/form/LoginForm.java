package com.example.webPOS.dto.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginForm {

    @Email
    @NotBlank
    private String id;

    @NotBlank
    private String password;

    public LoginForm() {
    }

    public LoginForm(String id, String passwd) {
        this.id = id;
        this.password = passwd;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String passwd) {
        this.password = passwd;
    }
}
