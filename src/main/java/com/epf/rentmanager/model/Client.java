package com.epf.rentmanager.model;

import java.time.LocalDate;

// + INT id PRIMARY KEY
//        AUTOINCREMENT
//        + VARCHAR lastname
//        + VARCHAR firstname
//        + VARCHAR email
//        + DATETIME birthdate

public class Client {
    private int id;
    private String lastname, firstname, email;
    private LocalDate birthdate;

    public Client() {
    }

    public Client(int id, String lastname, String firstname, String email, LocalDate birthdate) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.birthdate = birthdate;
    }

    public Client(String lastname, String firstname, String email, LocalDate birthdate) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.birthdate = birthdate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public String toString() {
        return "Client [id=" + id + ", lastname=" + lastname + ", firstname=" + firstname + ", email=" + email
                + ", birthdate=" + birthdate + "]";
    }
}
