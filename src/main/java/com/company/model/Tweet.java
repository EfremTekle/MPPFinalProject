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


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public User getOwner() {
		return owner;
	}


	public void setOwner(User owner) {
		this.owner = owner;
	}


	public List<Comment> getComments() {
		return comments;
	}


	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}


	public List<Like> getLikes() {
		return likes;
	}


	public void setLikes(List<Like> likes) {
		this.likes = likes;
	}


	public LocalDateTime getPostedTime() {
		return postedTime;
	}


	public void setPostedTime(LocalDateTime postedTime) {
		this.postedTime = postedTime;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public static int getCounter() {
		return counter;
	}


	public static void setCounter(int counter) {
		Tweet.counter = counter;
	}


	
}
