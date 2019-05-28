package com.company;

import com.company.model.Comment;
import com.company.model.Like;
import com.company.model.Tweet;
import com.company.model.User;
import com.company.utils.FunctionalUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FunctionalUtilTest {

    private List<User> users;
    private User user1;


    @BeforeEach
    void setUp() {
        user1 = new User();
        user1.setUsername("test1");
        user1.setPassword("password");


        users = new ArrayList<>();
        users.add(user1);

    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void getUserByUsername() {

        User user2 = FunctionalUtil.getUserByUsername.apply("test1", users);
        assert (user2.equals(user1));

    }

    @Test
    void isUserExist() {

        assertTrue(FunctionalUtil.isUserExists.apply("test1", users));

    }

    @Test
    void getUserByUsernameAndPassword() {

        User user2 = FunctionalUtil.getUserByUsernameAndPassword.apply("test1", "password", users);
        assert (user2.equals(user1));
    }

    @Test
    void addUserToList() {

        User user2 = new User();
        user2.setUsername("test2");
        user2.setPassword("password");

        users=FunctionalUtil.addUserToList.apply(user2,users);
        assertTrue (users.contains(user2));
    }

    @Test
    void removeUserFromList() {

        users=FunctionalUtil.removeUserFromList.apply(user1,users);
        assertFalse(FunctionalUtil.isUserExists.apply("test1", users));


    }


    @Test
    void sortUserTweets() {

        User user2 = new User("test2","password");
        Tweet tweet1 = new Tweet("new tweet1",user2, LocalDateTime.now());
        Tweet tweet2 =new Tweet("new tweet2",user2, LocalDateTime.now());
        Tweet tweet3 =new Tweet("new tweet3",user2, LocalDateTime.now());

        User user3 = new User("test2","password");
        Tweet tweet4 =new Tweet("new tweet4",user3, LocalDateTime.now());
        Tweet tweet5 =new Tweet("new tweet5",user3, LocalDateTime.now());
        Tweet tweet6 =new Tweet("new tweet6",user3, LocalDateTime.now());

        users.add(user2);
        users.add(user3);

        List<Tweet> tweetList = new ArrayList<>();
        tweetList.add(tweet6);
        tweetList.add(tweet5);
        tweetList.add(tweet4);
        tweetList.add(tweet3);
        tweetList.add(tweet2);
        tweetList.add(tweet1);



        List<Tweet> sortedTweets =FunctionalUtil.sortUserTweets.apply(users);
        assertEquals(sortedTweets,tweetList);
    }


    @Test
    void isTweetExist() {

        Tweet tweet1 = new Tweet("new tweet1",user1, LocalDateTime.now());
        tweet1.setId(1);

        List<Tweet> tweetList = new ArrayList<>();
        tweetList.add(tweet1);


        assertTrue(FunctionalUtil.isTweetExist.apply(1,tweetList));
    }

    @Test
    void getCommentMessage() {

        String command = "comment 1 heyy!";
        String[] commandArray=command.split(" ");
        assertEquals("heyy!",FunctionalUtil.getCommentMessage.apply(commandArray));


    }


    @Test
    void getTweetText() {

        String command = "tweet heyy!";
        String[] commandArray=command.split(" ");
        assertEquals("heyy!",FunctionalUtil.getTweetText.apply(commandArray));


    }


    @Test
    void getTweetsByHashTag() {

        User user2 = new User("test2","password");
        Tweet tweet1 = new Tweet("new tweet1 #sunny",user2, LocalDateTime.now());
        Tweet tweet2 =new Tweet("new tweet2 #sunny",user2, LocalDateTime.now());
        Tweet tweet3 =new Tweet("new tweet3",user2, LocalDateTime.now());

        User user3 = new User("test2","password");
        Tweet tweet4 =new Tweet("new tweet4",user3, LocalDateTime.now());
        Tweet tweet5 =new Tweet("new tweet5",user3, LocalDateTime.now());
        Tweet tweet6 =new Tweet("new tweet6",user3, LocalDateTime.now());

        users.add(user2);
        users.add(user3);

        List<Tweet> tweetList = new ArrayList<>();
        tweetList.add(tweet6);
        tweetList.add(tweet5);
        tweetList.add(tweet4);
        tweetList.add(tweet3);
        tweetList.add(tweet2);
        tweetList.add(tweet1);

        List<Tweet> hashtagedTweetList = new ArrayList<>();
        hashtagedTweetList.add(tweet2);
        hashtagedTweetList.add(tweet1);



        List<Tweet> foundHashtagedTweets =FunctionalUtil.getTweetsByHashTag.apply(tweetList,"#sunny");
        assertEquals(hashtagedTweetList,foundHashtagedTweets);

    }


    @Test
    void getTweetsByMentioning() {

        User user2 = new User("test2","password");
        Tweet tweet1 = new Tweet("new tweet1 @eren",user2, LocalDateTime.now());
        Tweet tweet2 =new Tweet("new tweet2 @eren",user2, LocalDateTime.now());
        Tweet tweet3 =new Tweet("new tweet3",user2, LocalDateTime.now());

        User user3 = new User("test2","password");
        Tweet tweet4 =new Tweet("new tweet4",user3, LocalDateTime.now());
        Tweet tweet5 =new Tweet("new tweet5",user3, LocalDateTime.now());
        Tweet tweet6 =new Tweet("new tweet6",user3, LocalDateTime.now());

        users.add(user2);
        users.add(user3);

        List<Tweet> tweetList = new ArrayList<>();
        tweetList.add(tweet6);
        tweetList.add(tweet5);
        tweetList.add(tweet4);
        tweetList.add(tweet3);
        tweetList.add(tweet2);
        tweetList.add(tweet1);

        List<Tweet> hashtagedTweetList = new ArrayList<>();
        hashtagedTweetList.add(tweet2);
        hashtagedTweetList.add(tweet1);



        List<Tweet> foundHashtagedTweets =FunctionalUtil.getTweetsByMentioning.apply(tweetList,"eren");
        assertEquals(hashtagedTweetList,foundHashtagedTweets);

    }


    @Test
    void hasTweetHashtag() {

        User user2 = new User("test2","password");
        Tweet tweet1 = new Tweet("new tweet1 #sunny",user2, LocalDateTime.now());

        assertTrue(FunctionalUtil.hasTweetHashtag.apply(tweet1,"#sunny"));

    }

    @Test
    void isTweetMentioningTheUser() {

        User user2 = new User("test2","password");
        Tweet tweet1 = new Tweet("new tweet1 @eren",user2, LocalDateTime.now());

        assertTrue(FunctionalUtil.isTweetMentioningTheUser.apply(tweet1,"eren"));

    }


    @Test
    void getTweetById() {



        User user3 = new User("test2","password");
        Tweet tweet4 =new Tweet("new tweet4",user3, LocalDateTime.now());
        tweet4.setId(5);
        Tweet tweet5 =new Tweet("new tweet5",user3, LocalDateTime.now());
        Tweet tweet6 =new Tweet("new tweet6",user3, LocalDateTime.now());



        List<Tweet> tweetList = new ArrayList<>();
        tweetList.add(tweet6);
        tweetList.add(tweet5);
        tweetList.add(tweet4);


        Tweet tweet =FunctionalUtil.getTweetById.apply(5,tweetList);
        assertEquals(tweet,tweet4);

    }


    @Test
    void concatTwoTweetListsAndSort() {
        User user2 = new User("test2","password");
        Tweet tweet1 = new Tweet("new tweet1 @eren",user2, LocalDateTime.now());
        Tweet tweet2 =new Tweet("new tweet2 @eren",user2, LocalDateTime.now());
        Tweet tweet3 =new Tweet("new tweet3",user2, LocalDateTime.now());

        User user3 = new User("test2","password");
        Tweet tweet4 =new Tweet("new tweet4",user3, LocalDateTime.now());
        Tweet tweet5 =new Tweet("new tweet5",user3, LocalDateTime.now());
        Tweet tweet6 =new Tweet("new tweet6",user3, LocalDateTime.now());

        users.add(user2);
        users.add(user3);

        List<Tweet> tweetList = new ArrayList<>();
        List<Tweet> tweetList1 = new ArrayList<>();
        List<Tweet> tweetList2 = new ArrayList<>();

        tweetList.add(tweet6);
        tweetList.add(tweet5);
        tweetList.add(tweet4);
        tweetList.add(tweet3);
        tweetList.add(tweet2);
        tweetList.add(tweet1);

        tweetList1.add(tweet6);
        tweetList1.add(tweet5);
        tweetList1.add(tweet4);
        tweetList2.add(tweet3);
        tweetList2.add(tweet2);
        tweetList2.add(tweet1);

        assertEquals(tweetList,FunctionalUtil.concatTwoTweetListsAndSort.apply(tweetList1,tweetList2));

    }


    @Test
    void addCommentToList() {

        List<Comment> commentList = new ArrayList<>();

        Comment comment = new Comment("Comment",user1);

        commentList=FunctionalUtil.addCommentToList.apply(comment,commentList);
        assertTrue (commentList.contains(comment));
    }


    @Test
    void addLikeToList() {

        List<Like> likes = new ArrayList<>();

        Like like = new Like(user1);

        likes=FunctionalUtil.addLikeToList.apply(like,likes);
        assertTrue (likes.contains(like));
    }


}