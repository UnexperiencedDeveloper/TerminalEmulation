package com.timprogrammiert.filesystem;

/**
 * @author tmatz
 */
public class FileObject extends BaseFileSystemObject{
    private String content;
    public FileObject(String name, String content) {
        super(name);
        this.content = content;
    }

    public FileObject(String name, String content, BaseFileSystemObject parent) {
        super(parent, name);
        this.content = content;
    }

    public String getContent(){
        return content;
    }

    public void setContent(String content){
        this.content = content;
    }
}
