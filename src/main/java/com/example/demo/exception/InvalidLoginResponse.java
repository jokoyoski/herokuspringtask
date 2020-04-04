package com.example.demo.exception;

public class InvalidLoginResponse {

    public InvalidLoginResponse(){
        this.username="Invalid username";
        this.password="invalid password";
    }
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;
}
