package com.example.mini_projet_java;

public class dashtableimp {
    private String name;
    private String lastname;
    private String remaindays;

    public dashtableimp(String name, String lastname, String remaindays) {
        this.name = name;
        this.lastname = lastname;
        this.remaindays = remaindays;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getRemaindays() {
        return remaindays;
    }
}
