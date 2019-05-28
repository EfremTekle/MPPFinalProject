package com.company.model;

import com.company.model.enums.Errors;

public class FakeData {

    private AppSystem appSystem;

    public FakeData(boolean init) {
        if (init) {
            appSystem = AppSystem.getInstance();

            User user1 = new User("fake1","123456");
            User user2 = new User("fake2","123456");


            if (!appSystem.addUser(user1)) System.out.println(Errors.USER_ALREADY_EXIST);
            if (!appSystem.addUser(user2)) System.out.println(Errors.USER_ALREADY_EXIST);


            System.out.println("fake data initialized:\n" + user1 +" \n"+user2 +"\n");



        }
    }


}
