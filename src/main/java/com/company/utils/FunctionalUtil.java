package com.company.utils;

import com.company.abstracts.TriFunction;
import com.company.model.Comment;
import com.company.model.Like;
import com.company.model.Tweet;
import com.company.model.User;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FunctionalUtil {

    public static BiFunction<String, List<User>, User> getUserByUsername = (username, userList) -> userList.stream().filter(u -> u.getUsername().equals(username)).findAny().get();


    public static BiFunction<String, List<User>, Boolean> isUserExists = (username, userList) -> userList.stream().anyMatch(u -> u.getUsername().equals(username));


    public static TriFunction<String, String, List<User>, User> getUserByUsernameAndPassword = (username, password, userList) -> userList.stream().filter(u -> u.getUsername().equals(username) && u.getPassword().equals(password)).findAny().orElse(null);


    public static BiFunction<User, List<User>, List<User>> addUserToList = (user, userList) -> Stream.concat(userList.stream(), Stream.of(user)).collect(Collectors.toList());


    public static BiFunction<User, List<User>, List<User>> removeUserFromList = (user, userList) -> userList.stream().filter(u -> !u.getUsername().equals(user.getUsername())).collect(Collectors.toList());

    public static Function<List<User>, List<Tweet>> sortUserTweets = (users) -> users.stream().flatMap(user -> user.getTweets().stream()).sorted(Comparator.comparing(Tweet::getId).reversed()).collect(Collectors.toList());

/*    public static Function<List<Tweet>, List<Tweet>> sortTweets = (users) ->
            users
                    .stream()
                    .sorted(Comparator.comparing(Tweet::getId).reversed()).collect(Collectors.toList());*/


    public static BiFunction<Integer, List<Tweet>, Boolean> isTweetExist = (id, tweetList) -> tweetList.stream().anyMatch(t -> t.getId() == id);


    public static Function<String[], String> getCommentMessage = (stringArray) -> Stream.of(stringArray).skip(2).collect(Collectors.joining(" "));

    public static Function<String[], String> getTweetText = (stringArray) -> Stream.of(stringArray).skip(1).collect(Collectors.joining(" "));

/*    public static Function<String[], List<String>> mentionFinder = (stringArray) ->
            Stream.of(stringArray).skip(1).filter(element->element.startsWith("@")).map(element->element.substring(1)).collect(Collectors.toList());

    public static Function<String[], List<String>> hashTagFinder = (stringArray) ->
            Stream.of(stringArray).skip(1).filter(element->element.startsWith("#")).map(element->element.substring(1)).collect(Collectors.toList());*/

    public static BiFunction<List<Tweet>, String, List<Tweet>> getTweetsByHashTag = (tweetList, hashtag) -> tweetList.stream().filter(tweet -> FunctionalUtil.hasTweetHashtag.apply(tweet, hashtag)).collect(Collectors.toList());


    public static BiFunction<List<Tweet>, String, List<Tweet>> getTweetsByMentioning = (tweetList, username) -> tweetList.stream().filter(tweet -> FunctionalUtil.isTweetMentioningTheUser.apply(tweet, username)).collect(Collectors.toList());


    public static BiFunction<Tweet, String, Boolean> hasTweetHashtag = (tweet, hashtag) -> Stream.of(tweet.getMessage().split(" ")).anyMatch(s -> s.equals(hashtag));

    public static BiFunction<Tweet, String, Boolean> isTweetMentioningTheUser = (tweet, username) -> Stream.of(tweet.getMessage().split(" ")).anyMatch(s -> s.equals("@" + username));


    public static BiFunction<Integer, List<Tweet>, Tweet> getTweetById = (tweetId, tweets) -> tweets.stream().filter(t -> t.getId() == tweetId).findAny().get();


    public static BiFunction<List<Tweet>, List<Tweet>, List<Tweet>> concatTwoTweetListsAndSort = (tweets1, tweets2) -> Stream.concat(tweets1.stream(), tweets2.stream()).sorted(Comparator.comparing(Tweet::getId).reversed()).collect(Collectors.toList());


    public static BiFunction<Comment, List<Comment>, List<Comment>> addCommentToList = (comment, commentList) -> Stream.concat(commentList.stream(), Stream.of(comment)).collect(Collectors.toList());

    public static BiFunction<Like, List<Like>, List<Like>> addLikeToList = (like, likes) -> Stream.concat(likes.stream(), Stream.of(like)).collect(Collectors.toList());

}
