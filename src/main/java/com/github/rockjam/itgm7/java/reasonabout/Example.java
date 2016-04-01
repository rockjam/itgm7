package com.github.rockjam.itgm7.java.reasonabout;

import com.github.rockjam.itgm7.java.Person;

public class Example {

    private final PersonDAO dao;

    public Example(PersonDAO dao) {
        this.dao = dao;
    }

    public String extractName() {
        Person found = dao.getByEmail("vash_typhoon@mail.ru");
        if(found != null) {
            return found.getName() + " " + found.getLastName();
        } else {
            return "No name";
        }
    }
}