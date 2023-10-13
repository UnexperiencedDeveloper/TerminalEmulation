package com.timprogrammiert.filesystem;

/**
 * @author tmatz
 */
public class DirectoryObject extends BaseFileSystemObject {

    public DirectoryObject(String name, BaseFileSystemObject parent) {
        super(parent,name);
    }
    public DirectoryObject(String name) {
        super(name);
    }
}
