package com.example.springBack.Application.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

@Entity
public class Message {
    @GeneratedValue
    @Id
    int id;
    String title;
    String text;
    LocalDate time;
    @ManyToOne
    @JsonIgnore
    private Person person;

    public Message()
    {}
    public Message(int id,String title, String text, LocalDate time) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setSurname(String text) {
        this.text = text;
    }



    public LocalDate getBirthday() {
        return time;
    }


    public void setBirthday(LocalDate time) {
        this.time = time;
    }



    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
