package com.timprogrammiert.filesystem;

import com.timprogrammiert.exceptions.FileNotExistsException;
import com.timprogrammiert.filesystem.permission.Permission;
import com.timprogrammiert.util.PermissionCreater;
import com.timprogrammiert.filesystem.permission.User;
import com.timprogrammiert.filesystem.permission.UserGroup;

import java.util.*;

/**
 * @author tmatz
 */
public abstract class BaseFileSystemObject {
    public final Map<String, BaseFileSystemObject> children;
    private BaseFileSystemObject parent;
    private String name;
    private User belongsToUser;
    private UserGroup belongsToUserGroup;
    private Permission permission;

    public BaseFileSystemObject(String name){
        this.children = new HashMap<>();
        this.name = name;
        parent = null;
        // TODO user should be set
    }

    public BaseFileSystemObject(BaseFileSystemObject parent, String name) {
        this.children = new HashMap<>();
        this.parent = parent;
        this.name = name;
    }
    public BaseFileSystemObject(BaseFileSystemObject parent, String name, User userOwner) {
        this.children = new HashMap<>();
        this.parent = parent;
        this.name = name;
        this.belongsToUser = userOwner;
        initPermission();
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

    private void initPermission(){
        permission = PermissionCreater.createStandardPermission();
    }

}
