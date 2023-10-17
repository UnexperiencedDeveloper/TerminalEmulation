package com.timprogrammiert.filesystem;

import com.timprogrammiert.filesystem.permission.Permission;
import com.timprogrammiert.filesystem.permission.User;

import java.util.Collection;

/**
 * @author tmatz
 */
public class DirectoryObject extends BaseFileSystemObject {
    public DirectoryObject(String name, User owner) {
        super(name, new Permission(owner, Permission.DEFAULT_DIRECTORY_PERMISSION));
    }
    public DirectoryObject(String name, BaseFileSystemObject parent) {
        super(parent,name);
    }
    public DirectoryObject(String name, BaseFileSystemObject parent, User owner) {
        super(parent,name, new Permission(owner, Permission.DEFAULT_DIRECTORY_PERMISSION));
    }

    public void addNewDirectory(DirectoryObject directoryToAdd){
        // TODO CHECK IF DIRECTORY ALREDY EXISTS
        directoryToAdd.setParent(directoryToAdd);
        children.put(directoryToAdd.getName(), directoryToAdd);
    }
    public void addNewFile(FileObject fileToAdd){
        // TODO CHECK IF FILE ALREDY EXISTS
        fileToAdd.setParent(fileToAdd);
        children.put(fileToAdd.getName(), fileToAdd);
    }

    public Collection<BaseFileSystemObject> getAllChildren(){
        return children.values();
    }
}
