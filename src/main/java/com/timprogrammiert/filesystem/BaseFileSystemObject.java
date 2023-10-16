package com.timprogrammiert.filesystem;

import com.timprogrammiert.exceptions.FileNotExistsException;
import com.timprogrammiert.exceptions.NotADirectoryException;

import java.util.*;

/**
 * @author tmatz
 */
public abstract class BaseFileSystemObject {
    public final Map<String, BaseFileSystemObject> children;
    private BaseFileSystemObject parent;
    private String name;

    public BaseFileSystemObject(String name){
        this.children = new HashMap<>();
        this.name = name;
        parent = null;
    }

    public BaseFileSystemObject(BaseFileSystemObject parent, String name) {
        this.children = new HashMap<>();
        this.parent = parent;
        this.name = name;
    }

    public BaseFileSystemObject getSpecificChildren(String childrenName) throws FileNotExistsException {
        if(this instanceof DirectoryObject){
            return children.get(childrenName);
        }else {
            throw new FileNotExistsException();
        }

    }

    public Collection<BaseFileSystemObject> getAllChilldren(){
        return children.values();
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    /*
    public void addNewDirectory(BaseFileSystemObject dirToAdd){
        if(this instanceof DirectoryObject){
            dirToAdd.setParent(this);
            children.put(dirToAdd.getName(), dirToAdd);
        }else {
            throw new UnsupportedOperationException(this.getName() + " is a File");
        }
    }*/
/*
    public void addNewFile(BaseFileSystemObject fileToAdd) {
        if(this instanceof DirectoryObject){
            children.put(fileToAdd.getName(), fileToAdd);
        }else {
            throw new UnsupportedOperationException(this.getName() + " is a File");
        }
    }*/

    public BaseFileSystemObject getParent(){
        return parent;
    }

    public void setParent(BaseFileSystemObject parentObject){
        this.parent = parentObject;
    }
}
