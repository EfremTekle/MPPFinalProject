package com.company.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class Comment {
    private String textOfComment;
    private User user;
    private LocalDateTime localDateTime;
    private int id;
    private static int counter;


    public Comment(String textOfComment, User user) {
        this.textOfComment = textOfComment;
        this.user = user;
        this.localDateTime = LocalDateTime.now();
        counter++;
        this.id = counter;
    }

    @Override
    public String toString() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");


        return "\n\n" +
                "\t\t" + user.getUsername() +" commented: "+textOfComment + '\n' +
                "\t\tat: " + localDateTime.format(formatter)+'\n' ;
    }

	public String getTextOfComment() {
		return textOfComment;
	}

	public void setTextOfComment(String textOfComment) {
		this.textOfComment = textOfComment;
	}

	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}

	public void setId(int id) {
		this.id = id;
	}

	public static void setCounter(int counter) {
		Comment.counter = counter;
	}

	public User getUser() {
		// TODO Auto-generated method stub
		return user;
	}

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	public int getId() {
		return id;
	}

	public static int getCounter() {
		return counter;
	}

	

	
}
