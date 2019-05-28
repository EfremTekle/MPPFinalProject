package com.company.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Tweet {


    public Tweet(String message, User owner, LocalDateTime postedTime) {
        this.message = message;
        this.owner = owner;
        this.owner.getTweets().add(this);
        this.postedTime = postedTime;
        counter++;
        this.id = counter;
        this.comments = new ArrayList<>();
        this.likes = new ArrayList<>();

    }

    private String message;
    private User owner;
    private List<Comment> comments;
    private List<Like> likes;
    private LocalDateTime postedTime;
    private int id;

    private static int counter;


    @Override
    public String toString() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");


        return "\n\nTweet{\n" +
                "\t"+owner.getUsername()+" tweeted: " + message + '\n' +
                "\tat: " + postedTime.format(formatter) + "\n" +
                "\tid: " + id + '\n' +
                "\t|\n" +
                "\t|\n" +
                "\t---Likes: " + likes + "\n" +

                "\t---Comments" + comments;
    }
}
