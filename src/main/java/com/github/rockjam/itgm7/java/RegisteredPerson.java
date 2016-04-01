package com.github.rockjam.itgm7.java;

public class RegisteredPerson {

    private String name;

    private String lastName;

    private String email;

    private String nickname;

    public RegisteredPerson(String name, String lastName, String email, String nickname) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.nickname = nickname;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }
}
