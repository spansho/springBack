package dto;

import java.time.LocalDate;

public class Message {
    int id;
    String title;
    String text;
    LocalDate time;

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



    public LocalDate getTime() {
        return time;
    }

    public void setBirthday(LocalDate time) {
        this.time = time;
    }
}
