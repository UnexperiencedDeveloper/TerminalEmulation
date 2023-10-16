package com.timprogrammiert.filesystem;

import com.timprogrammiert.exceptions.FileNotExistsException;

import java.util.Collection;

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

    public BaseFileSystemObject getSpecificChildren(String childrenName) throws FileNotExistsException {

        BaseFileSystemObject returnObject = children.get(childrenName);
        if(returnObject == null) throw new FileNotExistsException();
        return returnObject;
    }

    public Collection<BaseFileSystemObject> getAllChilldren(){
        return children.values();
    }
}
