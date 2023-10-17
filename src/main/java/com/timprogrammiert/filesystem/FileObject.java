package com.timprogrammiert.filesystem;

import com.timprogrammiert.filesystem.permission.Permission;
import com.timprogrammiert.filesystem.permission.User;

/**
 * @author tmatz
 */
public class FileObject extends BaseFileSystemObject{
    private String content;
    private Permission permission;
    public FileObject(String name, String content, User owner) {
        super(name, new Permission(owner, Permission.DEFAULT_FILE_PERMISSION));
        this.content = content;
    }

    public FileObject(String name, String content, BaseFileSystemObject parent) {
        super(parent, name);
        this.content = content;
    }

    public FileObject(String name, String content, BaseFileSystemObject parent, User userOwner) {
        super(parent,name, new Permission(userOwner, Permission.DEFAULT_FILE_PERMISSION));
        this.content = content;
    }


    public String getContent(){
        return content;
    }

    public void setContent(String content){
        this.content = content;
    }
}
