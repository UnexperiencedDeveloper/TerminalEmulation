package com.timprogrammiert.filesystem;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tmatz
 */
public abstract class BaseFileSystemObject {
    private Map<String, BaseFileSystemObject> children;
    private String name;

    public BaseFileSystemObject(String name){
        this.children = new HashMap<>();
        this.name = name;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public void addNewDirectory(BaseFileSystemObject dirToAdd){
        if(this instanceof DirectoryObject){
            children.put(dirToAdd.getName(), dirToAdd);
        }else {
            throw new UnsupportedOperationException(this.getName() + " is a File");
        }
    }

    public void addNewFile(BaseFileSystemObject fileToAdd) {
        if(this instanceof DirectoryObject){
            children.put(fileToAdd.getName(), fileToAdd);
        }else {
            throw new UnsupportedOperationException(this.getName() + " is a File");
        }
    }
}
