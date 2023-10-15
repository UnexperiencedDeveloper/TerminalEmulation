package com.timprogrammiert.util;

import com.timprogrammiert.exceptions.FileNotExistsException;
import com.timprogrammiert.exceptions.NotADirectoryException;
import com.timprogrammiert.filesystem.BaseFileSystemObject;
import com.timprogrammiert.host.Host;

import java.util.List;

/**
 * @author tmatz
 */
public class PathResolver {

    /**
     * Used to list Files with a Relative Path for one Directory
     */
    public static BaseFileSystemObject resolveSingleRelativePath(String childrenName, Host host) throws NotADirectoryException {
        return host.getCurrentDirectory().getSpecificChildren(childrenName);
    }

    /**
     * Used to list Files with a Relative Path of multiple Directories
     */
    public static BaseFileSystemObject resolveMultiRelativePath(List<String> argList, Host host) throws NotADirectoryException, FileNotExistsException {
        String[] subDirectoriesStrings = argList.get(0).split("/");
        BaseFileSystemObject recursiveDir = host.getCurrentDirectory();
        for (String dirName: subDirectoriesStrings) {
            recursiveDir = recursiveDir.getSpecificChildren(dirName);
            if(recursiveDir == null) throw new FileNotExistsException();
        }
        return recursiveDir;
    }
}
