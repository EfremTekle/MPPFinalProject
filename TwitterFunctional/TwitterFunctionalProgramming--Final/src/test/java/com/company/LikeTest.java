package com.company;

import com.company.model.Like;
import com.company.model.Tweet;
import com.company.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class LikeTest {

    private User user1;
    private User user2;

    private Like like1;

    @BeforeEach
    void setUp() {
        user1 = new User();
        user1.setUsername("test1");
        user1.setPassword("password");


        user2 = new User();
        user2.setUsername("test2");
        user2.setPassword("password");


        like1 = new Like(user1);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void toStringTest() {
        assertEquals(like1.toString(),"test1");
    }

    @Test
    void getLocalDateTime() {
        assertEquals(like1.getLocalDateTime().getMinute(),LocalDateTime.now().getMinute());
    }

    @Test
    void getUser() {
        assertEquals(like1.getUser(),user1);
    }
}