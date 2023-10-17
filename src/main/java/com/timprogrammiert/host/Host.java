package com.timprogrammiert.host;

import com.timprogrammiert.filesystem.BaseFileSystemObject;
import com.timprogrammiert.filesystem.DirectoryObject;
import com.timprogrammiert.filesystem.VirtualFileSystem;
import com.timprogrammiert.filesystem.permission.User;
import com.timprogrammiert.filesystem.permission.UserGroup;

import java.util.List;

/**
 * @author tmatz
 */
public class Host {
    private final VirtualFileSystem virtualFileSystem;
    private BaseFileSystemObject currentDirectory;
    private List<User> users;
    private List<UserGroup> userGroups;
    private User currentUser;

    public Host() {
        virtualFileSystem = new VirtualFileSystem();

        // On startup start on root directory
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

    private void initTestUser(){
        User testUser = new User();
        currentUser = testUser;
    }
}
