package com.example.a19524301_minhhong_th;

import java.util.Date;

public class Account {
    private String email;
    private String name;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Account(String email, String name, Date birthday, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public Account() {
    }
}
