package com.timprogrammiert.filesystem;

/**
 * @author tmatz
 */
public class VirtualFileSystem {
    String[] fileSystemStructure = new String[]{"etc", "var", "home"};
    BaseFileSystemObject rootObject = new DirectoryObject("/");

    public VirtualFileSystem() {

    }

    private void createFileSystemStructure(){
        for (String dirName: fileSystemStructure) {
            rootObject.addNewDirectory(new DirectoryObject(dirName));
        }
    }
}
