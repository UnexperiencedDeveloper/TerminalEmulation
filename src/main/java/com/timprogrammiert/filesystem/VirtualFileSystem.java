package com.timprogrammiert.filesystem;

import com.timprogrammiert.util.DirectoryInfo;

/**
 * @author tmatz
 */
public class VirtualFileSystem {
    String[] fileSystemStructure = new String[]{"etc", "var", "home"};
    DirectoryObject rootObject;

    public VirtualFileSystem() {
        rootObject = new DirectoryObject("/");
        createFileSystemStructure();
    }

    private void createFileSystemStructure(){
        DirectoryObject etcDir = new DirectoryObject("etc", rootObject);
        DirectoryObject varDir = new DirectoryObject("var", rootObject);
        DirectoryObject homeDir = new DirectoryObject("home", rootObject);
        rootObject.addNewDirectory(etcDir);
        rootObject.addNewDirectory(varDir);
        rootObject.addNewDirectory(homeDir);
        DirectoryObject testDirectory = new DirectoryObject("test", etcDir);
        etcDir.addNewDirectory(testDirectory);

        FileObject anotherTestDir = new FileObject("AnotherTest", "Content",testDirectory);
        testDirectory.addNewFile(anotherTestDir);
    }

    public DirectoryObject getRootFileSystem(){
        return (DirectoryObject) rootObject;
    }
}
