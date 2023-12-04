package com.springapps.jpaexamples.twitterapp;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Tweet {
    @Id
    @GeneratedValue
    private long Id;

    @Column
    private String text;

    @JoinColumn(name = "User_id")
    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "tweet")
    private List<Comment> comments;


    public Tweet(String text) {
        this.text = text;
    }

    public Tweet() {
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "Id=" + Id +
                ", text='" + text + '\'' +
                ", user=" + user +
                '}';
    }
}
