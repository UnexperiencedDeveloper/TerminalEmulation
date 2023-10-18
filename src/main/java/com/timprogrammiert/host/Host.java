package com.timprogrammiert.host;

import com.timprogrammiert.filesystem.BaseFileSystemObject;
import com.timprogrammiert.filesystem.DirectoryObject;
import com.timprogrammiert.filesystem.VirtualFileSystem;
import com.timprogrammiert.filesystem.permission.User;
import com.timprogrammiert.filesystem.permission.UserGroup;

import java.util.*;

/**
 * @author tmatz
 */
public class Host {
    private final VirtualFileSystem virtualFileSystem;
    private BaseFileSystemObject currentDirectory;
    // Element 0 is always root user
    private Map<String,User> users;
    private Map<String, UserGroup> userGroups;
    private User currentUser;

    public Host() {
        users = new HashMap<>();
        userGroups = new HashMap<>();

        addNewUser(new User("root"));
        initTestUser();

        // On startup start on root directory
        virtualFileSystem = new VirtualFileSystem(this);
        currentDirectory = virtualFileSystem.getRootFileSystem();

    }

    public DirectoryObject getCurrentDirectory() {
        return (DirectoryObject) currentDirectory;
    }

    public void setCurrentDirectory(BaseFileSystemObject currentDirectory){
        this.currentDirectory = currentDirectory;
    }

    public BaseFileSystemObject getRootFileSystem(){
       return virtualFileSystem.getRootFileSystem();
    }
    public User getCurrentUser(){return currentUser;}

    public void addNewUser(User newUser){
        users.put(newUser.getUserName(), newUser);
    }
    public Optional<User> getUserByName(String userName){
        if(users.containsKey(userName)){
            return Optional.ofNullable(users.get(userName));
        }else {
            return Optional.empty();
        }
    }
    public void addNewGroup(UserGroup newUserGroup){
        userGroups.put(newUserGroup.getGroupName(), newUserGroup);
    }

    private void initTestUser(){
        currentUser = new User("Tim");
    }
}
