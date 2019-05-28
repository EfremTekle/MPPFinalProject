package com.company.model;

import com.company.utils.FunctionalUtil;

import java.util.ArrayList;
import java.util.List;


public class AppSystem {

    private List<User> users;
    private User currentUser;
    private static AppSystem instance;

    public static AppSystem getInstance() {
        if (instance == null) {
            instance = new AppSystem();
            return instance;
        } else {
            return instance;
        }
    }

    private AppSystem() {
        this.users = new ArrayList<>();
    }


    public User getUserByUsername(String username) {
        return FunctionalUtil.getUserByUsername.apply(username, getAllUsers());
    }

    public boolean isUserExist(String username) {
        return FunctionalUtil.isUserExists.apply(username, getAllUsers());
    }


    public boolean addUser(User user) {


        if (isUserExist(user.getUsername())) {
            return false;
        }
        this.setUsers(FunctionalUtil.addUserToList.apply(user, getAllUsers()));
        return true;

    }

    public List<User> getAllUsers() {

        return this.users;

    }

    public boolean logIn(String username, String password) {
        setCurrentUser(FunctionalUtil.getUserByUsernameAndPassword.apply(username, password, getAllUsers()));

        if (getCurrentUser() == null) {
            return false;
        } else {
            return true;
        }

    }


    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public void clearSystem() {
        users = new ArrayList<>();
        currentUser = null;

    }


    public void setUsers(List<User> users) {
        this.users = users;
    }
}
