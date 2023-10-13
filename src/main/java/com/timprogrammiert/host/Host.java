package com.timprogrammiert.host;

import com.timprogrammiert.filesystem.BaseFileSystemObject;
import com.timprogrammiert.filesystem.VirtualFileSystem;

/**
 * @author tmatz
 */
public class Host {
    private final VirtualFileSystem virtualFileSystem;
    private BaseFileSystemObject currentDirectory;

    public Host() {
        virtualFileSystem = new VirtualFileSystem();

        // On startup start on root directory
        currentDirectory = virtualFileSystem.getRootFileSystem();
    }

    public BaseFileSystemObject getCurrentDirectory() {
        return currentDirectory;
    }

    public void setCurrentDirectory(BaseFileSystemObject currentDirectory){
        this.currentDirectory = currentDirectory;
    }

    public BaseFileSystemObject getRootFileSystem(){
       return virtualFileSystem.getRootFileSystem();
    }
}
