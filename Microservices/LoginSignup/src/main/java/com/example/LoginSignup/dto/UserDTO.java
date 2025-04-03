package com.example.LoginSignup.dto;

import lombok.Data;
@Data
public class UserDTO {
    private int id;
    private String userName;
    private String email;

    private String role;
    private String password;

    public UserDTO(int id, String userName, String email, String role, String password) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.role = role;
        this.password = password;
    }

    public UserDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
