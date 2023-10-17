package com.timprogrammiert.filesystem.permission;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String userName;
    private List<UserGroup> userGroups;


    public User(String userName) {
        this.userName = userName;
        userGroups = new ArrayList<>();
        UserGroup defaultGroup = new UserGroup(userName, this);
        userGroups.add(defaultGroup);

    }

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
