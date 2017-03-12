package com.example.yassine.randon_ili;

import java.util.Date;

/**
 * Created by yassine on 03/02/2017.
 */

public class Randonneur {
    private String name;
    private String username;
    private String password;
    private String email;
    private Date birthdate;
    private String gender;

    public Randonneur(){}

    public Randonneur(String password, String username, String name, Date birthdate, String email, String gender) {
        this.password = password;
        this.username = username;
        this.name = name;
        this.birthdate = birthdate;
        this.email = email;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
