package com.timprogrammiert.filesystem;

import com.timprogrammiert.filesystem.permission.User;
import com.timprogrammiert.host.Host;

/**
 * @author tmatz
 */
public class VirtualFileSystem {
    private String[] fileSystemStructure = new String[]{"etc", "var", "home"};
    private DirectoryObject rootObject;
    private Host host;
    private User rootUser;

    public VirtualFileSystem(Host host) {
        rootUser = host.getUserByName("root").get();
        rootObject = new DirectoryObject("/", rootUser);
        this.host = host;
        createFileSystemStructure();
    }

    private void createFileSystemStructure(){
        DirectoryObject etcDir = new DirectoryObject("etc", rootObject, rootUser);
        DirectoryObject varDir = new DirectoryObject("var", rootObject, rootUser);
        DirectoryObject homeDir = new DirectoryObject("home", rootObject, rootUser);
        rootObject.addNewDirectory(etcDir);
        rootObject.addNewDirectory(varDir);
        rootObject.addNewDirectory(homeDir);
        DirectoryObject testDirectory = new DirectoryObject("test", etcDir, host.getCurrentUser());
        etcDir.addNewDirectory(testDirectory);

        FileObject anotherTestDir = new FileObject("AnotherTest", "Content",testDirectory, host.getCurrentUser());
        testDirectory.addNewFile(anotherTestDir);

        FileObject testFileObject = new FileObject("testFile", "Content", rootObject, host.getCurrentUser());
        rootObject.addNewFile(testFileObject);
    }

    public DirectoryObject getRootFileSystem(){
        return rootObject;
    }
}
