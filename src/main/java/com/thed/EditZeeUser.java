package com.thed;

import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.List;

/**
 * Utility to edit zephyr users
 */
public class EditZeeUser {

    public static void main(String as[]) throws Exception {
        if (as.length < 4){
            showUsage();
            return;
        }

        //loadProperties();

        List<User> users = CsvParser.parseCsv(as[1], true);

        for (User usr : users) {
            HttpClient httpClient = new DefaultHttpClient();
            String url = as[0]+ "/flex/services/rest/v1/user/" + usr.getId();
            HttpPut putRequest = new HttpPut(url);
            System.out.println("updating to URL " + url);

            Gson gson = new Gson();
            StringEntity input = new StringEntity(gson.toJson(usr));

            input.setContentType("application/json");
            putRequest.setHeader("Authorization", getAuthorization(as[2], as[3]));
            putRequest.setEntity(input);

            HttpResponse response = httpClient.execute(putRequest);

            if (response.getStatusLine().getStatusCode() != 204) {
                System.out.println("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
            }else{
                System.out.println("user with id " + usr.getId() + " successfully updated");
            }

        }
    }


    private static String getAuthorization(String userName, String password) {
        String auth = userName + ":" + password;
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(Charset.forName("US-ASCII")));
        return "Basic " + new String(encodedAuth);
    }

    private static void showUsage() {
        System.out.println("Incorrect input params");
        System.out.println(" ZEE Rest client for user update");
        System.out.println("\t 1. Server URL ex. " + "https://demo.yourzephyr.com");
        System.out.println("\t 2. CSV file path (absolute) ex./Users/Pravin/resources/users.csv" +
                " Plz get sample csv format from https://github.com/zephyrdeveloper/zeeuserupload/blob/master/src/main/resources/usersupdate.csv");
        System.out.println("\t 3. User ID ex. test.manager");
        System.out.println("\t 4. User password ex. test.manager");
        System.out.println("AN Example call on a Unix like system is as following");
        System.out.println("\t java -cp .:/Users/Pravin/Documents/temp/libs/* com.thed.EditZeeUser " +
                " https://demo.yourzephyr.com /Users/Pravin/Documents/temp/libs/users.csv test.manager test.manager");
    }
}
