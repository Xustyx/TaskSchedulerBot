package com.xsty.telegram.alert;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by XST on 22/10/2017.
 */
@Entity
public class Alert implements Serializable {

    private Long id;

    private String author;
    private Long chatId;
    private String message;
    private Date date;

    public Alert(){
        this("",0L,"",new Date());
    }

    public Alert(String author, Long chatId, String message, Date date) {
        this.author = author;
        this.chatId = chatId;
        this.message = message;
        this.date = date;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    @Column(nullable = false)
    public String getAuthor() {
        return author;
    }

    @Column(nullable = false)
    public long getChatId() {
        return chatId;
    }

    @Column(nullable = false)
    public String getMessage() {
        return message;
    }

    @Column(nullable = false)
    public Date getDate() {
        return date;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Alert alert = (Alert) o;

        if (id != null ? !id.equals(alert.id) : alert.id != null) return false;
        if (author != null ? !author.equals(alert.author) : alert.author != null) return false;
        if (chatId != null ? !chatId.equals(alert.chatId) : alert.chatId != null) return false;
        if (message != null ? !message.equals(alert.message) : alert.message != null) return false;
        return date != null ? date.equals(alert.date) : alert.date == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (chatId != null ? chatId.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        return "Alert{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", chatId=" + chatId +
                ", message='" + message + '\'' +
                ", date=" + date +
                '}';
    }
}
