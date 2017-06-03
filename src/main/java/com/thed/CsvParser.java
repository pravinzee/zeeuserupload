package com.thed;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 *
 */
public class CsvParser {


    String csvFile = "/Users/Pravin/Documents/dev/zee/zeerestcsvclient/src/main/resources/users.csv";

    //csv format
    // lastName,firstName,email,username,loginName,location,roleId,roleName

    public static List<User> parseCsv(String csvFile, boolean edit) {

        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        List<User> users = new ArrayList<User>();
        try {
            br = new BufferedReader(new FileReader(csvFile));

            int i = 0;
            while ((line = br.readLine()) != null) {

                if (i == 0) {  //header line
                    i++;
                    continue;
                }
                String[] userData = line.split(cvsSplitBy);
                if (userData.length < 4) {
                    throw new IllegalArgumentException("invalid input data " + userData.toString());
                }


                User user = null;
                if (edit){
                    try{
                        String[] updatedUsers =  Arrays.copyOfRange(userData, 1, userData.length);
                        user = new User(updatedUsers);
                        user.setId(new Long(userData[0]));
                    }catch (Exception e){
                        throw new IllegalArgumentException("invalid input data || could not parser user id" + userData.toString());
                    }
                }else{
                    user = new User(userData);
                }
                users.add(user);
                i++;
            }

        } catch (FileNotFoundException e) {
            System.out.println("file not found at path : " + csvFile);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return users;

    }


}



