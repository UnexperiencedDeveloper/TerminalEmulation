package com.timprogrammiert.filesystem;

import com.timprogrammiert.util.DirectoryInfo;

/**
 * @author tmatz
 */
public class VirtualFileSystem {
    String[] fileSystemStructure = new String[]{"etc", "var", "home"};
    BaseFileSystemObject rootObject;
    BaseFileSystemObject testDirectory;

    public VirtualFileSystem() {
        rootObject = new DirectoryObject("/");
        testDirectory = new DirectoryObject("test", rootObject.getSpecificChildren("etc"));
        createFileSystemStructure();
    }

    private void createFileSystemStructure(){
        for (String dirName: fileSystemStructure) {
            rootObject.addNewDirectory(new DirectoryObject(dirName, rootObject));
        }
        rootObject.getSpecificChildren("etc").addNewDirectory(testDirectory);
    }

    public BaseFileSystemObject getRootFileSystem(){
        return rootObject;
    }
}
