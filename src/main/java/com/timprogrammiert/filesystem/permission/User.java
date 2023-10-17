package com.timprogrammiert.filesystem.permission;

import java.util.List;

public class User {
    private String userName;
    private List<UserGroup> userGroups;



    public String getUserName(){
        return userName;
    }

    public List<UserGroup> getUserGroups(){
        return userGroups;
    }

    public void setUserName(String newUserName){
        // TODO CHECK IF USERNAME IS AVAILABLE
        userName = newUserName;
    }
}
