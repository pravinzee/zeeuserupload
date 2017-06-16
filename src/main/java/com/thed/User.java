package com.thed;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * User entity Wrapper
 */
 class User implements Serializable {


    private Long id;
    private String title;
    private String firstName;
    private String lastName;
    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String username;
    private String loginName;
    private String location;
    private Boolean credentialsExpired;
    private Set<Role> roles = new HashSet<Role>(0);

    // lastName,firstName,email,username,loginName,location,roleId,roleName
    User(String[] userData) {

        this.title = userData[0];
        this.lastName = userData[1];
        this.firstName = userData[2];
        this.email = userData[3];
        this.username = userData[4];

//        if (userData.length > 5) {
//            this.loginName = userData[5];
//        }
        if (userData.length > 5) {
            this.location = userData[5];
        }
        Role role = new Role(3l, "tester");

        if (userData.length > 6) {
            try {
                role.id = new Long(userData[6]);

            } catch (Exception e) {
                //parsing issue
            }
        }

        if (userData.length > 7) {
            role.name = userData[7];
        }

        if (userData.length > 8) {
            if (userData[8] != null && ! ((userData[8].toString().equals("true") || userData[8].toString().equals("false")))){
                throw new IllegalArgumentException("invalid input data " + userData.toString() + " 9th param must be either true or false");
            }
            this.credentialsExpired = new Boolean(userData[8]);
        }else{
            this.credentialsExpired = false;
        }

        roles.add(role);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}


class Role {
    Long id;
    String name;

    Role(Long roleId, String name) {
        this.id = roleId;
        this.name = name;
    }
}
