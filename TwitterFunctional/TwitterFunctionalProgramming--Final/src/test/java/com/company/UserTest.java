package com.company;

import com.company.model.AppSystem;
import com.company.model.Tweet;
import com.company.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user1;
    private User user2;
    private Tweet tweet1;
    private Tweet tweet2;
    private Tweet tweet3;

    @BeforeEach
    void setUp() {
        user1 = new User();
        user1.setUsername("test1");
        user1.setPassword("password");


        user2 = new User();
        user2.setUsername("test2");
        user2.setPassword("password");


        tweet1 = new Tweet("tweet1", user1, LocalDateTime.now());
        tweet2 = new Tweet("tweet2", user1, LocalDateTime.now());
        tweet3 = new Tweet("tweet3", user2, LocalDateTime.now());

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void followTestFollowers() {
        user1.follow(user2,true);
        assertTrue(user2.getFollowers().contains(user1));
    }

    @Test
    void followTestFollowing() {
        user1.follow(user2,true);
        assertTrue(user1.getFollowing().contains(user2));
    }

    @Test
    void unfollowTestFollowers() {
        user1.follow(user2,true);
        user1.unfollow(user2);
        assertFalse(user2.getFollowers().contains(user1));
    }

    @Test
    void unfollowTestFollowing() {
        user1.follow(user2,true);
        user1.unfollow(user2);
        assertFalse(user1.getFollowing().contains(user2));
    }

    @Test
    void setPassword() {
        user1.setPassword("differentPassword");
        assertTrue(user1.getPassword().equals("differentPassword"));
    }

    @Test
    void toStringTest() {
        assertEquals(user1.toString(),"test1");
    }

    @Test
    void getWall() {
        List<Tweet> list = new ArrayList<Tweet>();
        list.add(tweet2);
        list.add(tweet1);
        assertEquals(user1.getWall(),list);
    }

    @Test
    void getNews() {
        user1.follow(user2,true);
        List<Tweet> list = new ArrayList<Tweet>();
        list.add(tweet3);
        list.add(tweet2);
        list.add(tweet1);
        assertEquals(user1.getNews(),list);

    }

    @Test
    void getFollowers() {
        user1.follow(user2,true);
        assertTrue(user2.getFollowers().contains(user1));
    }

    @Test
    void sendFriendRequest() {
        user1.sendFriendRequest(user2);
        assertTrue(user2.getFriendsRequests().contains(user1));
    }

    @Test
    void acceptFriendRequestSender() {
        user1.sendFriendRequest(user2);
        user2.acceptFriend(user1);
        assertTrue(user1.getFriends().contains(user2));
    }

    @Test
    void acceptFriendRequestReceiver() {
        user1.sendFriendRequest(user2);
        user2.acceptFriend(user1);
        assertTrue(user2.getFriends().contains(user1));
    }

    @Test
    void acceptFriendRequestDeleteCheck() {
        user1.sendFriendRequest(user2);
        user2.acceptFriend(user1);
        assertFalse(user2.getFriendsRequests().contains(user1));
    }

    @Test
    void unfriendRequestSender() {
        user1.sendFriendRequest(user2);
        user2.acceptFriend(user1);
        user2.unfriend(user1);
        assertFalse(user1.getFriends().contains(user2));
    }

    @Test
    void unfriendRequestReceiver() {
        user1.sendFriendRequest(user2);
        user2.acceptFriend(user1);
        user2.unfriend(user1);
        assertFalse(user2.getFriends().contains(user1));
    }

    @Test
    void declineFriendRequestSender() {
        user1.sendFriendRequest(user2);
        user2.declineFriend(user1);
        assertFalse(user1.getFriends().contains(user2));
    }

    @Test
    void declineFriendRequestReceiver() {
        user1.sendFriendRequest(user2);
        user2.declineFriend(user1);
        assertFalse(user2.getFriends().contains(user1));
    }

    @Test
    void declineFriendRequestDeleteCheck() {
        user1.sendFriendRequest(user2);
        user2.declineFriend(user1);
        assertFalse(user2.getFriendsRequests().contains(user1));
    }

    @Test
    void setUsername() {
        user1.setUsername("differentUserName");
        assertTrue(user1.getUsername().equals("differentUserName"));
    }

    @Test
    void setFollowers() {
        List<User> followers = new ArrayList<User>();
        followers.add(user2);
        user1.setFollowers(followers);
        assertEquals(user1.getFollowers(),followers);
    }

    @Test
    void setFollowing() {
        List<User> following = new ArrayList<User>();
        following.add(user2);
        user1.setFollowing(following);
        assertEquals(user1.getFollowing(),following);
    }

    @Test
    void setTweets() {
        List<Tweet> list = new ArrayList<Tweet>();
        list.add(tweet1);
        user1.setTweets(list);
        assertEquals(user1.getTweets(),list);
    }

    @Test
    void setFriends() {
        List<User> list = new ArrayList<User>();
        list.add(user2);
        user1.setFriends(list);
        assertEquals(user1.getFriends(),list);

    }

    @Test
    void setFriendsRequests() {
        List<User> list = new ArrayList<User>();
        list.add(user2);
        user1.setFriendsRequests(list);
        assertEquals(user1.getFriendsRequests(),list);
    }

    @Test
    void getUsername() {
        assertTrue(user1.getUsername().equals("test1"));
    }

    @Test
    void getPassword() {
        assertTrue(user1.getPassword().equals("password"));
    }

    @Test
    void getFollowing() {
        user1.follow(user2,true);
        assertTrue(user1.getFollowing().contains(user2));
    }

    @Test
    void getTweets() {
        List<Tweet> list = new ArrayList<Tweet>();
        list.add(tweet1);
        list.add(tweet2);
        assertEquals(user1.getTweets(),list);
    }

    @Test
    void getFriends() {
        List<User> friends = new ArrayList<User>();
        friends.add(user2);
        user1.sendFriendRequest(user2);
        user2.acceptFriend(user1);
        assertEquals(user1.getFriends(),friends);
    }

    @Test
    void getFriendsRequests() {
        List<User> friends = new ArrayList<User>();
        friends.add(user1);
        user1.sendFriendRequest(user2);
        assertEquals(user2.getFriendsRequests(),friends);
    }
}