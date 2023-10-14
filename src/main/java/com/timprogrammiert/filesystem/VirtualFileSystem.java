package com.timprogrammiert.filesystem;

import com.timprogrammiert.util.DirectoryInfo;

/**
 * @author tmatz
 */
public class VirtualFileSystem {
    String[] fileSystemStructure = new String[]{"etc", "var", "home"};
    BaseFileSystemObject rootObject;

    public VirtualFileSystem() {
        rootObject = new DirectoryObject("/");
        createFileSystemStructure();
    }

    private void createFileSystemStructure(){
        BaseFileSystemObject etcDir = new DirectoryObject("etc", rootObject);
        BaseFileSystemObject varDir = new DirectoryObject("var", rootObject);
        BaseFileSystemObject homeDir = new DirectoryObject("home", rootObject);
        rootObject.addNewDirectory(etcDir);
        rootObject.addNewDirectory(varDir);
        rootObject.addNewDirectory(homeDir);
        BaseFileSystemObject testDirectory = new DirectoryObject("test", etcDir);
        etcDir.addNewDirectory(testDirectory);
    }

    public BaseFileSystemObject getRootFileSystem(){
        return rootObject;
    }
}
