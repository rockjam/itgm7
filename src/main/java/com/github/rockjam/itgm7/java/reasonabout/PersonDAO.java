package com.github.rockjam.itgm7.java.reasonabout;

import com.github.rockjam.itgm7.java.Person;

public interface PersonDAO {
    public Person getByEmail(String email);
}
