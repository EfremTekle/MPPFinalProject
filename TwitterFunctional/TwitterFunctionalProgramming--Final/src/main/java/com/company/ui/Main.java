package com.company.ui;

import com.company.model.*;
import com.company.model.enums.Errors;
import com.company.utils.FunctionalUtil;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import static com.company.model.enums.Errors.*;

public class Main {

    public static void main(String[] args) {

        Command.printCommands();
        AppSystem appSystem = AppSystem.getInstance();

        /////////////////---FAKE DATA---/////////////////////////////
        new FakeData(true);
        /////////////////////////////////////////////////////////


        /////////////////---ADMIN---//////////////////////////////
        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword("123456");
        if (!appSystem.addUser(admin)) System.out.println("Error: " + USER_ALREADY_EXIST);
        /////////////////////////////////////////////////////////

        Scanner in = new Scanner(java.lang.System.in);

        boolean quit = false;


        do {


            if (appSystem.getCurrentUser() != null) {
                System.out.print("@" + appSystem.getCurrentUser().getUsername() + " ");
            } else {
                System.out.print("type command: ");
            }


            String nextLine = in.nextLine();
            String commandArray[] = nextLine.split(" ");
            String command = commandArray[0];

            switch (command) {

                case "help":
                    Command.printCommands();
                    break;

                case "createuser":
                    if (commandArray.length < 2) {
                        System.out.println("username can't be empty");
                    } else {

                        if(appSystem.getCurrentUser()!=null){
                            System.out.println(YOU_SHOULD_LOG_OUT_FIRST);
                        }else{

                            User user = new User();
                            user.setUsername(commandArray[1]);
                            String pass = user.listenPassword();
                            if (pass != null) {
                                if (user.setPassword(pass)) {
                                    System.out.println("User created!");
                                    if (!appSystem.addUser(user)) System.out.println("Error: " + USER_ALREADY_EXIST);
                                }
                            }
                        }

                    }

                    break;

                case "login":
                    if (appSystem.getCurrentUser() == null) {

                        if (commandArray.length < 3) {
                            System.out.println("login command error: parameters are missing");
                        } else {
                            boolean logInResult = appSystem.logIn(commandArray[1], commandArray[2]);
                            if (logInResult) {
                                System.out.println(appSystem.getCurrentUser().getUsername() + " you are in");
                            } else {
                                System.out.println("Error: \nNo such a user or wrong password");
                            }
                        }
                    } else {
                        System.out.println("You should logout first!");
                    }

                    break;

                case "logout":
                    appSystem.setCurrentUser(null);
                    break;

                case "quit":
                    quit = true;
                    break;

                case "tweet":
                    if (commandArray.length < 2) {
                        System.out.println("tweet can't be empty");

                    } else {
                        if (appSystem.getCurrentUser() == null) {
                            System.out.println(NOT_LOGGED_IN);
                        } else {
                            String tweetText = FunctionalUtil.getTweetText.apply(commandArray);

                               /* FunctionalUtil.mentionFinder.apply(commandArray);
                                FunctionalUtil.hashTagFinder.apply(commandArray);*/


                            new Tweet(tweetText, appSystem.getCurrentUser(), LocalDateTime.now());


                        }

                    }
                    break;


                case "gethashtag":
                    if (commandArray.length < 2) {
                        System.out.println("hashtag can't be empty");
                        //todo move all error messages to ENUMs

                    } else {
                        if (appSystem.getCurrentUser() == null) {
                            System.out.println(NOT_LOGGED_IN);
                        } else {

                            if (commandArray[1].startsWith("#")) {
                                List<Tweet> allUsersSortedTweets = FunctionalUtil.sortUserTweets.apply(appSystem.getAllUsers());

                                System.out.println(FunctionalUtil.getTweetsByHashTag.apply(allUsersSortedTweets, commandArray[1]));
                            } else {
                                System.out.println("'" + commandArray[1] + "'" + " " + IS_NOT_A_HASHTAG);

                            }


                        }

                    }
                    break;


                case "follow":
                    if (commandArray.length < 2) {
                        System.out.println("username can't be empty");

                    } else {
                        if (appSystem.getCurrentUser() == null) {
                            System.out.println(NOT_LOGGED_IN);
                        } else {
                            if (appSystem.isUserExist(commandArray[1])) {

                                appSystem.getCurrentUser().follow(appSystem.getUserByUsername(commandArray[1]), true);
                            } else {
                                System.out.println(Errors.USER_NOT_EXISTS);
                            }
                        }
                    }
                    break;

                case "unfollow":
                    if (commandArray.length < 2) {
                        System.out.println("username can't be empty");

                    } else {
                        if (appSystem.getCurrentUser() == null) {
                            System.out.println(NOT_LOGGED_IN);
                        } else {
                            if (appSystem.isUserExist(commandArray[1])) {
                                appSystem.getCurrentUser().unfollow(appSystem.getUserByUsername(commandArray[1]));
                            } else {
                                System.out.println(Errors.USER_NOT_EXISTS);
                            }
                        }

                    }
                    break;

                case "getallusers":

                    if (appSystem.getCurrentUser() != null) {
                        System.out.println(appSystem.getAllUsers());
                    } else {
                        System.out.println(NOT_LOGGED_IN);
                    }
                    break;

                case "getmywall":

                    if (appSystem.getCurrentUser() != null) {
                        System.out.println(appSystem.getCurrentUser().getWall());


                    } else {
                        System.out.println(Errors.NOT_LOGGED_IN);

                    }
                    break;


                case "getfollowers":

                    if (appSystem.getCurrentUser() != null) {
                        System.out.println(appSystem.getCurrentUser().getFollowers());

                    } else {
                        System.out.println(Errors.NOT_LOGGED_IN);

                    }
                    break;

                case "getfollowings":

                    if (appSystem.getCurrentUser() != null) {
                        System.out.println(appSystem.getCurrentUser().getFollowing());

                    } else {
                        System.out.println(Errors.NOT_LOGGED_IN);

                    }
                    break;

                case "sendfriendrequest":
                    if (commandArray.length < 2) {
                        System.out.println("username can't be empty");

                    } else {
                        if (appSystem.getCurrentUser() == null) {
                            System.out.println(NOT_LOGGED_IN);
                        } else {
                            if (appSystem.isUserExist(commandArray[1])) {

                                appSystem.getCurrentUser().sendFriendRequest(appSystem.getUserByUsername(commandArray[1]));
                            } else {
                                System.out.println(Errors.USER_NOT_EXISTS);
                            }
                        }

                    }
                    break;

                case "getfriendrequests":

                    if (appSystem.getCurrentUser() != null) {
                        System.out.println(appSystem.getCurrentUser().getFriendsRequests());
                    } else {
                        System.out.println(NOT_LOGGED_IN);
                    }
                    break;


                case "getfriends":

                    if (appSystem.getCurrentUser() != null) {
                        System.out.println(appSystem.getCurrentUser().getFriends());
                    } else {
                        System.out.println(NOT_LOGGED_IN);
                    }
                    break;

                case "acceptfriend":
                    if (commandArray.length < 2) {
                        System.out.println("username can't be empty");

                    } else {

                        if (appSystem.getCurrentUser() != null) {
                            if (FunctionalUtil.isUserExists.apply(commandArray[1], appSystem.getAllUsers())) {
                                appSystem.getCurrentUser().acceptFriend(appSystem.getUserByUsername(commandArray[1]));
                            } else {
                                System.out.println(USER_NOT_EXISTS);
                            }
                        } else {
                            System.out.println(NOT_LOGGED_IN);
                        }
                    }
                    break;

                case "declinefriend":

                    if (commandArray.length < 2) {
                        System.out.println("username can't be empty");

                    } else {

                        if (appSystem.getCurrentUser() != null) {
                            if (FunctionalUtil.isUserExists.apply(commandArray[1], appSystem.getAllUsers())) {
                                appSystem.getCurrentUser().declineFriend(appSystem.getUserByUsername(commandArray[1]));
                            } else {
                                System.out.println(USER_NOT_EXISTS);
                            }
                        } else {
                            System.out.println(NOT_LOGGED_IN);
                        }
                    }
                    break;

                case "unfriend":

                    if (commandArray.length < 2) {
                        System.out.println("username can't be empty");

                    } else {

                        if (appSystem.getCurrentUser() != null) {
                            if (FunctionalUtil.isUserExists.apply(commandArray[1], appSystem.getAllUsers())) {
                                appSystem.getCurrentUser().unfriend(appSystem.getUserByUsername(commandArray[1]));
                            } else {
                                System.out.println(USER_NOT_EXISTS);
                            }
                        } else {
                            System.out.println(NOT_LOGGED_IN);
                        }
                    }
                    break;


                case "getwall":

                    if (commandArray.length < 2) {
                        System.out.println("username can't be empty");
                    } else {

                        if (appSystem.getCurrentUser() != null) {

                            if (FunctionalUtil.isUserExists.apply(commandArray[1], appSystem.getAllUsers())) {
                                System.out.println(appSystem.getUserByUsername(commandArray[1]).getWall());

                            } else {
                                System.out.println(USER_NOT_EXISTS);
                            }


                        } else {
                            System.out.println(Errors.NOT_LOGGED_IN);
                        }
                    }

                    break;


                case "news":

                    if (appSystem.getCurrentUser() != null) {
                        System.out.println(appSystem.getCurrentUser().getNews());

                    } else {
                        System.out.println(Errors.NOT_LOGGED_IN);
                    }

                    break;


                case "comment":

                    if (commandArray.length < 3) {
                        System.out.println(TWEET_ID_AND_COMMENT_TEXT_CANNOT_BE_EMPTY);
                    } else {

                        try {
                            int tweetId = Integer.valueOf(commandArray[1]);

                            if (appSystem.getCurrentUser() != null) {

                                if (FunctionalUtil.isTweetExist.apply(tweetId, appSystem.getCurrentUser().getNews())) {

                                    Comment comment = new Comment(FunctionalUtil.getCommentMessage.apply(commandArray), appSystem.getCurrentUser());

                                    Tweet tweet = FunctionalUtil.getTweetById.apply(tweetId, appSystem.getCurrentUser().getNews());
                                    tweet.setComments(FunctionalUtil.addCommentToList.apply(comment, tweet.getComments()));

                                } else {
                                    System.out.println(Errors.NO_SUCH_TWEET_IN_YOUR_NEWS_ID);
                                }
                            } else {
                                System.out.println(Errors.NOT_LOGGED_IN);
                            }
                        } catch (NumberFormatException e) {
                            System.out.println(Errors.TWEET_ID_SHOULD_BE_A_NUMBER);
                        }
                    }

                    break;


                case "like":

                    if (commandArray.length < 2) {
                        System.out.println(TWEET_ID_CANNOT_BE_EMPTY);
                    } else {

                        try {
                            int tweetId = Integer.valueOf(commandArray[1]);

                            if (appSystem.getCurrentUser() != null) {

                                if (FunctionalUtil.isTweetExist.apply(tweetId, appSystem.getCurrentUser().getNews())) {

                                    Tweet tweet = FunctionalUtil.getTweetById.apply(tweetId, appSystem.getCurrentUser().getNews());
                                    Like like = new Like(appSystem.getCurrentUser());

                                    if (!tweet.getLikes().contains(like)) {
                                        tweet.setLikes(FunctionalUtil.addLikeToList.apply(like, tweet.getLikes()));

                                    } else {
                                        System.out.println(Errors.YOU_ALREADY_LIKED_IT);
                                    }


                                } else {
                                    System.out.println(Errors.NO_SUCH_TWEET_IN_YOUR_NEWS_ID);
                                }
                            } else {
                                System.out.println(Errors.NOT_LOGGED_IN);
                            }
                        } catch (NumberFormatException e) {
                            System.out.println(Errors.TWEET_ID_SHOULD_BE_A_NUMBER);
                        }
                    }

                    break;


                case "share":

                    if (commandArray.length < 2) {
                        System.out.println(TWEET_ID_CANNOT_BE_EMPTY);
                    } else {

                        try {
                            int tweetId = Integer.valueOf(commandArray[1]);

                            if (appSystem.getCurrentUser() != null) {

                                if (FunctionalUtil.isTweetExist.apply(tweetId, appSystem.getCurrentUser().getNews())) {

                                    Tweet tweetToShare = FunctionalUtil.getTweetById.apply(tweetId, appSystem.getCurrentUser().getNews());
                                    new Tweet("[retweet] " + tweetToShare.getOwner().getUsername() + ": " + tweetToShare.getMessage(), appSystem.getCurrentUser(), LocalDateTime.now());

                                } else {
                                    System.out.println(Errors.NO_SUCH_TWEET_IN_YOUR_NEWS_ID);
                                }
                            } else {
                                System.out.println(Errors.NOT_LOGGED_IN);
                            }
                        } catch (NumberFormatException e) {
                            System.out.println(Errors.TWEET_ID_SHOULD_BE_A_NUMBER);
                        }
                    }

                    break;

                case "":

                    break;


                default:

                    System.out.println("Invalid command");

            }

        } while (!quit);

        java.lang.System.out.println("Bye-bye!");
    }


}
