package com.company;


import com.company.model.AppSystem;
import com.company.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AppSystemTest {

    private User user1;
    private User user2;
    private User userSameWith1;

    private AppSystem appSystem ;

    @BeforeEach
    void setUp() {
        user1 = new User();
        user1.setUsername("test1");
        user1.setPassword("password");


        user2 = new User();
        user2.setUsername("test2");
        user2.setPassword("password");


        userSameWith1 = new User();
        userSameWith1.setUsername("test1");
        userSameWith1.setPassword("password");

        appSystem = AppSystem.getInstance();
        appSystem.clearSystem();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addUser() {



        appSystem.addUser(user1);
        assertTrue(appSystem.getAllUsers().contains(user1));
    }

    @Test
    void addExistingUser() {

        appSystem.addUser(user1);
        assertFalse(appSystem.addUser(userSameWith1));
    }


    @Test
    void getAllUsers() {

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        appSystem.addUser(user1);
        appSystem.addUser(user2);

        assertTrue(appSystem.getAllUsers().containsAll(users));
    }

    @Test
    void logIn() {
        appSystem.addUser(user1);
        assertTrue(appSystem.logIn(user1.getUsername(), user1.getPassword()));
    }

    @Test
    void logOut(){
        appSystem.addUser(user1);
        appSystem.logIn("test","test");
        assertNull(appSystem.getCurrentUser());
    }

/*    @Test
    void getCurrentUser() {
    }

    @Test
    void setCurrentUser() {
    }*/
}
