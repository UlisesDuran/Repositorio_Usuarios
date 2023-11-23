package com.uduran.models;

public class Usuario {
    private String username;
    private String password;
    private String mail;
    private Long id;

    public Usuario() {
    }

    public Usuario(String username, String password, String mail, Long id) {
        this.username = username;
        this.password = password;
        this.mail = mail;
        this.id = id;
    }

    public String getUsername(){
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id + " | " + username + " | " + password + " | " + mail;
    }
}
