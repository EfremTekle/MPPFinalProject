package com.company.model;

import com.company.utils.FunctionalUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.company.model.enums.Errors.*;

@Setter
@Getter
public class User {

    private String username;
    private String password;
    private List<User> followers;
    private List<User> following;
    private List<Tweet> tweets;
    private List<User> friends;
    private List<User> friendsRequests;


    //private List<User> friends;


    public User(String username, String password) {
        this.username = username;
        this.password = password;

        initArrays();
    }

    private void initArrays() {
        this.tweets = new ArrayList<>();
        this.followers = new ArrayList<>();
        this.following = new ArrayList<>();
        this.friends = new ArrayList<>();
        this.friendsRequests = new ArrayList<>();

    }

    public void follow(User user,boolean checkIfAlreadyFollowing) {
        if (!FunctionalUtil.isUserExists.apply(user.getUsername(), getFollowing())) {
            if (user.getUsername().equals(this.getUsername())) {
                System.out.println(YOU_CANNOT_FOLLOW_YOURSELF);
            } else {
                setFollowing(FunctionalUtil.addUserToList.apply(user, getFollowing()));
                user.setFollowers(FunctionalUtil.addUserToList.apply(this, user.getFollowers()));
            }

        } else {
            if(checkIfAlreadyFollowing) System.out.println(YOU_ALREADY_FOLLOW_THE_USER);
        }
    }

    public void unfollow(User user) {
        if (FunctionalUtil.isUserExists.apply(user.getUsername(), getFollowing())) {
            setFollowing(FunctionalUtil.removeUserFromList.apply(user, getFollowing()));
            user.setFollowers(FunctionalUtil.removeUserFromList.apply(this, user.getFollowers()));
        } else {
            System.out.println(YOU_ALREADY_DONT_FOLLOW_THE_USER);

        }
    }

    public User() {
        initArrays();

    }

    public boolean setPassword(String password) {
        if (password.length() >= 6) {
            this.password = password;
            return true;
        }
        System.out.println("AppSystem tried to set a password which has less than 6 chars, something is wrong!");
        return false;

    }


    public String listenPassword() {
        boolean cancel = false;

        do {
            System.out.println("set password: ");
            String password = new Scanner(System.in).nextLine();
            if (password.equals("cancel")) cancel = true;
            if (!cancel) {
                if (password.length() < 6) {
                    System.out.println("Password should be min 6 characters");
                } else {
                    return password;

                }
            }

        } while (!cancel);
        return password;

    }

    @Override
    public String toString() {
        return username;
    }

    public List<Tweet> getWall() {
        //return FunctionalUtil.sortTweets.apply(getTweets());

        List<Tweet> friendTweets = FunctionalUtil.sortUserTweets.apply(getFriends());

        List<Tweet> mentionedTweets = FunctionalUtil.getTweetsByMentioning.apply(friendTweets, this.getUsername());

        return FunctionalUtil.concatTwoTweetListsAndSort.apply(mentionedTweets, getTweets());


    }

    public List<Tweet> getNews() {
        List<Tweet> followingTweets = FunctionalUtil.sortUserTweets.apply(getFollowing());
        return FunctionalUtil.concatTwoTweetListsAndSort.apply(followingTweets, getWall());
    }

    public List<User> getFollowers() {
        return followers;
    }


    public void sendFriendRequest(User user) {
        if (!FunctionalUtil.isUserExists.apply(user.getUsername(), getFriends())) {
            if (!FunctionalUtil.isUserExists.apply(user.getUsername(), getFriendsRequests())) {
                if (!FunctionalUtil.isUserExists.apply(this.getUsername(), user.getFriendsRequests())) {
                    if (!user.getUsername().equals(this.getUsername())) {

                        user.setFriendsRequests(FunctionalUtil.addUserToList.apply(this, user.getFriendsRequests()));
                        follow(user,false);

                    } else {
                        System.out.println(YOU_CANNOT_BE_FRIENDS_WITH_YOURSELF);
                    }
                } else {
                    System.out.println(YOU_ALREADY_SENT_FRIEND_REQUEST);
                }
            } else {
                System.out.println(YOU_HAVE_FRIEND_REQUEST_FROM_THIS_USER);
            }
        } else {
            System.out.println(YOU_ARE_ALREADY_FRIENDS);
        }
    }

    public void acceptFriend(User user) {


        if (FunctionalUtil.isUserExists.apply(user.getUsername(), getFriendsRequests())) {
            this.setFriends(FunctionalUtil.addUserToList.apply(user, getFriends()));
            this.setFriendsRequests(FunctionalUtil.removeUserFromList.apply(user, getFriendsRequests()));
            user.setFriends(FunctionalUtil.addUserToList.apply(this, user.getFriends()));

            follow(user,false);

        } else {
            System.out.println(THERE_IS_NOT_SUCH_FRIEND_REQUEST);
        }

    }

    public void unfriend(User user) {

        if (FunctionalUtil.isUserExists.apply(user.getUsername(), getFriends())) {
            this.setFriends(FunctionalUtil.removeUserFromList.apply(user, getFriends()));
            user.setFriends(FunctionalUtil.removeUserFromList.apply(this, user.getFriends()));

            unfollow(user);

        } else {
            System.out.println(THERE_IS_NOT_SUCH_FRIEND);
        }

    }


    public void declineFriend(User user) {

        if (FunctionalUtil.isUserExists.apply(user.getUsername(), getFriendsRequests())) {
            this.setFriendsRequests(FunctionalUtil.removeUserFromList.apply(user, getFriendsRequests()));

        } else {
            System.out.println(THERE_IS_NOT_SUCH_FRIEND_REQUEST);
        }

    }

}
