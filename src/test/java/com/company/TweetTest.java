package com.company;

import com.company.model.Comment;
import com.company.model.Like;
import com.company.model.Tweet;
import com.company.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TweetTest {
    private User user1;
    private User user2;

    private Tweet tweet1;
    private Comment comment1;
    private Like like1;

    @BeforeEach
    void setUp() {
        user1 = new User();
        user1.setUsername("test1");
        user1.setPassword("password");


        user2 = new User();
        user2.setUsername("test2");
        user2.setPassword("password");


        tweet1 = new Tweet("tweet1", user1, LocalDateTime.now());
        comment1 = new Comment("comment1",user1);
        like1 = new Like(user1);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void toStringTest() {
        assertEquals(tweet1.toString().substring(0,31),"\n\n"+"Tweet{\n" + "\ttest1 tweeted: tweet1");
    }

    @Test
    void setMessage() {
        tweet1.setMessage("a Different Message");
        assertEquals(tweet1.getMessage(),"a Different Message");
    }

    @Test
    void setOwner() {
        tweet1.setOwner(user2);
        assertEquals(tweet1.getOwner(),user2);
    }

    @Test
    void setComments() {
        List<Comment> list = new ArrayList<Comment>();
        list.add(comment1);
        tweet1.setComments(list);
        assertEquals(tweet1.getComments(),list);
    }

    @Test
    void setLikes() {
        List<Like> list = new ArrayList<Like>();
        list.add(like1);
        tweet1.setLikes(list);
        assertEquals(tweet1.getLikes(),list);
    }

    @Test
    void setPostedTime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        tweet1.setPostedTime(localDateTime);
        assertEquals(tweet1.getPostedTime(),localDateTime);
    }

    @Test
    void setId() {
        tweet1.setId(1);
        assertEquals(tweet1.getId(),1);

    }

    @Test
    void getMessage() {
        tweet1.setMessage("a Different Message");
        assertEquals(tweet1.getMessage(),"a Different Message");
    }

    @Test
    void getOwner() {
        tweet1.setOwner(user2);
        assertEquals(tweet1.getOwner(),user2);
    }

    @Test
    void getComments() {
        List<Comment> list = new ArrayList<Comment>();
        list.add(comment1);
        tweet1.setComments(list);
        assertEquals(tweet1.getComments(),list);
    }

    @Test
    void getLikes() {
        List<Like> list = new ArrayList<Like>();
        list.add(like1);
        tweet1.setLikes(list);
        assertEquals(tweet1.getLikes(),list);
    }

    @Test
    void getPostedTime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        tweet1.setPostedTime(localDateTime);
        assertEquals(tweet1.getPostedTime(),localDateTime);
    }

    @Test
    void getId() {
        tweet1.setId(1);
        assertEquals(tweet1.getId(),1);
    }
}