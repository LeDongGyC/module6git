package com.codegym.backend.payload.response;


import java.util.List;

public class JwtResponse {
    private String token;
    private Integer id;
    private String username;
    private List<String> roles;
    private String name;
    private Boolean changePassword;

    public JwtResponse() {
    }

    public JwtResponse(String token, Integer id, String username, List<String> roles, String name, Boolean changePassword) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.roles = roles;
        this.name = name;
        this.changePassword = changePassword;
    }

    public Boolean getChangePassword() {
        return changePassword;
    }

    public void setChangePassword(Boolean changePassword) {
        this.changePassword = changePassword;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;

    }

}
