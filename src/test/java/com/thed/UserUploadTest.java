package com.thed;

/**
 * Bulk User upload through CSV test
 */
public class UserUploadTest {


    public static void main(String as[]) throws Exception {
        testUserUplaod();

    }

    public static void testUserUplaod() throws Exception {
        String[] params = new String[4];
        params[0] = "http://localhost:8081";
        params[1] = "/Users/Pravin/Documents/dev/zee-tools/zeeuserupload/src/main/resources/users.csv";
        params[2] = "test.manager";
        params[3] = "test.manager";

        ZeeRestClient.main(params);
        //System.out.println("user uploaded successfully");
    }

}
