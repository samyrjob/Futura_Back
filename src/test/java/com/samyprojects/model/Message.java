package com.samyprojects.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

// import org.springframework.data.annotation.Id;
// import org.springframework.data.jdbc.core.mapping.AggregateReference;
// import org.springframework.data.relational.core.mapping.Column;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="messages")
public class Message {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Or another appropriate strategy
    Integer id;
    int length;
    String content;

    @Column(name="PUBLISHED_ON")  // Specify the column name explicitly
    private LocalDateTime publishedOn;

    @ManyToOne
    @JoinColumn(name = "UTILISATOR") // Foreign key column in DB
    private Utilisator utilisator;
    //* the Spring data JDBC method :
    // private AggregateReference<Utilisator, Integer> utilisator;


    public Utilisator getUtilisator() {
        return utilisator;
    }


    public void setUtilisator(Utilisator utilisator) {
        this.utilisator = utilisator;
    }


    public Message(int length, String content, Utilisator utilisator) {
        this.length = length;
        this.content = content;
        this.utilisator = utilisator;
        this.publishedOn = LocalDateTime.now();
    }


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public int getLength() {
        return length;
    }


    public void setLength(int length) {
        this.length = length;
    }


    public String getContent() {
        return content;
    }


    public void setContent(String content) {
        this.content = content;
    }


    public LocalDateTime getPublishedOn() {
        return publishedOn;
    }


    public void setPublishedOn(LocalDateTime publishedOn) {
        this.publishedOn = publishedOn;
    }








    
}
