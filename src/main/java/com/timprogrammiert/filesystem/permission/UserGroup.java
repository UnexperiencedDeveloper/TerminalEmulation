package com.timprogrammiert.filesystem.permission;

import java.util.ArrayList;
import java.util.List;

public class UserGroup {
    private String groupName;
    private List<User> userList;

    public UserGroup(String groupName) {
        this.groupName = groupName;
        userList = new ArrayList<>();
    }

    public UserGroup(String groupName, User userToAdd) {
        this.groupName = groupName;
        userList = new ArrayList<>();
        addUser(userToAdd);
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public boolean checkUserIsPartOfGroup(String userName) {
        for (User user: userList) {
            if(user.getUserName().equals(userName)) return true;
        }
        return false;
    }

    public void addUser(User userToAdd) {
        if(userList.contains(userToAdd)) {
            System.out.println("Should be an Exception!! \nUser already exists in this Group");
            return;
        }
        userList.add(userToAdd);
    }
}
