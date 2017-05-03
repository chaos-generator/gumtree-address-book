package com.gumtree.augusto.pojo;

import java.time.ZonedDateTime;

public class Contact {
    private final String name;
    private final String gender;
    private final ZonedDateTime dob;

    public Contact(String name, String gender, ZonedDateTime dob) {
        this.name = name;
        this.gender = gender;
        this.dob = dob;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public ZonedDateTime getDob() {
        return dob;
    }
}
