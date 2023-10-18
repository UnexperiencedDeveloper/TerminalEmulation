package com.timprogrammiert.filesystem;

import com.timprogrammiert.exceptions.FileNotExistsException;
import com.timprogrammiert.filesystem.permission.Permission;

import java.util.*;

/**
 * @author tmatz
 */
public abstract class BaseFileSystemObject {
    public final Map<String, BaseFileSystemObject> children;
    private BaseFileSystemObject parent;
    private String name;
    private Permission permissions;

    public BaseFileSystemObject(String name, Permission permission){
        this.children = new HashMap<>();
        this.name = name;
        parent = null;
        this.permissions = permission;
        // TODO user should be set
    }

    public BaseFileSystemObject(BaseFileSystemObject parent, String name) {
        this.children = new HashMap<>();
        this.parent = parent;
        this.name = name;
    }
    public BaseFileSystemObject(BaseFileSystemObject parent, String name, Permission permission) {
        this.children = new HashMap<>();
        this.parent = parent;
        this.name = name;
        this.permissions = permission;
    }
    public BaseFileSystemObject getSpecificChildren(String childrenName) throws FileNotExistsException {

        BaseFileSystemObject returnObject = children.get(childrenName);
        if(returnObject == null) throw new FileNotExistsException();
        return returnObject;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }


    public BaseFileSystemObject getParent(){
        return parent;
    }

    public void setParent(BaseFileSystemObject parentObject){
        this.parent = parentObject;
    }

    public Permission getPermissions() {
        return permissions;
    }
}
