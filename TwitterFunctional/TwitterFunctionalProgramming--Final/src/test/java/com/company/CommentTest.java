package com.company;

import com.company.model.Comment;
import com.company.model.Like;
import com.company.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CommentTest {

    private User user1;
    private User user2;

    private Comment comment1;

    @BeforeEach
    void setUp() {
        user1 = new User();
        user1.setUsername("test1");
        user1.setPassword("password");


        user2 = new User();
        user2.setUsername("test2");
        user2.setPassword("password");

        comment1 = new Comment("comment1",user1);
        comment1.setId(1);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void toStringTest() {
        assertEquals(comment1.toString().substring(0,29),"\n\n" + "\t\t"+"test1 commented: comment1");
    }

    @Test
    void getTextOfComment() {
        assertEquals(comment1.getTextOfComment(),"comment1");
    }

    @Test
    void getUser() {
        assertEquals(comment1.getUser(),user1);
    }

    @Test
    void getLocalDateTime() {
        assertEquals(comment1.getLocalDateTime().getMinute(), LocalDateTime.now().getMinute());
    }

    @Test
    void getId() {


        assertEquals(comment1.getId(),1);
    }
}