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
}
