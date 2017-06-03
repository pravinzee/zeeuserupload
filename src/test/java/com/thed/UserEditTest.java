package com.thed;

/**
 * Created by Pravin on 6/3/17.
 * Copyright D Inc. 2017 , use it at own risk
 */
public class UserEditTest {

    public static void main(String as[]) throws Exception {
        testUserUplaod();

    }

    public static void testUserUplaod() throws Exception {
        String[] params = new String[4];
        params[0] = "http://localhost:8081";
        params[1] = "/Users/Pravin/Documents/dev/zee-tools/zeeuserupload/src/main/resources/usersupdate.csv";
        params[2] = "test.manager";
        params[3] = "test.manager";
        EditZeeUser.main(params);
        System.out.println("user edited successfully");
    }

}
