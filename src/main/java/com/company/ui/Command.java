package com.company.ui;


public class Command {


    public static void printCommands(){

        System.out.println("\n");
        System.out.println("-------------Console Twitter----------");
        System.out.println("\n");

        System.out.println("createuser [username]              - to create a user");
        System.out.println("login [username] [password]        - to login");
        System.out.println("logout                             - to logout");
        System.out.println("tweet [tweet]                      - to send a tweet");
        System.out.println("follow [username]                  - to follow a user");
        System.out.println("getfollowers                       - to get list of followers");
        System.out.println("getfollowings                      - to get list of followings");
        System.out.println("unfollow [username]                - to unfollow a user");
        System.out.println("sendfriendrequest [username]       - to send a friend request");
        System.out.println("acceptfriend [username]            - to accept a friend request");
        System.out.println("declinefriend [username]           - to decline a friend request");
        System.out.println("getfriendrequests                  - to see all friend requests");
        System.out.println("getfriends                         - to see all friends");
        System.out.println("unfriend [username]                - to send a friend request");
        System.out.println("getallusers                        - get all users");
        System.out.println("getmywall                          - to get user's own wall");
        System.out.println("getwall [username]                 - to view somebody's wall");
        System.out.println("news                               - to view news");
        System.out.println("comment [tweetid] [comment]        - to comment somebody's tweet");
        System.out.println("like [tweetid]                     - to like somebody's tweet ");
        System.out.println("share [tweetid]                    - to share somebody's tweet");
/*        System.out.println("delete [tweetid]                   - to delete your own tweet");
        System.out.println("delete [commentid]                 - to delete your own comment");*/
        System.out.println("gethashtag [hashtag]               - to view all tweets which have mentioned hashtag");

        //todo add profile, see profile, change toString method inside Users



        System.out.println("help                               - see available commands");

        System.out.println("cancel                             - cancel the current operation");
        System.out.println("quit                               - quit the app");
        System.out.println("\n");


        System.out.println("\n");



    }
}
