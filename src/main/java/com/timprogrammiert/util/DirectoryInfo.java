package com.timprogrammiert.util;

import com.timprogrammiert.exceptions.FileNotExistsException;
import com.timprogrammiert.exceptions.NotADirectoryException;
import com.timprogrammiert.filesystem.BaseFileSystemObject;
import com.timprogrammiert.filesystem.DirectoryObject;
import com.timprogrammiert.host.Host;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author tmatz
 */
public class DirectoryInfo {
    public static String getAbsolutePathByFileSystem(BaseFileSystemObject fileSystemObject){
            BaseFileSystemObject parent = fileSystemObject;
            List<String> allParents = new ArrayList<>();
            allParents.add(parent.getName());

            while (parent.getParent() != null) {
                parent = parent.getParent();
                allParents.add(parent.getName());
            }

            // Reverse to ensure absolute Path is in correct order
            // dirNames get stored fromn child to Parent, we want from Parent to child
            Collections.reverse(allParents);

            StringBuilder output = new StringBuilder();
            for (String dirName : allParents) {
                output.append(dirName);
                if(dirName.equals("/")) continue;
                // Check if other Directorys still exists
                if (!dirName.equals(allParents.get(allParents.size() - 1))) {
                    output.append("/");
                }
            }

            return output.toString();
    }

    /**
     * Each SubPath is resolved to its BaseFileSystemObject, and its children and so on.
     * If the provided Children Path Contains a FileObject which is not the target of the path, Exception
     * will be thrown
     * @param subDir List of subDirectories names like ['path', 'to', 'file' ]
     * @return Requested Directory by absolute Path
     * Takes in a List of subDirectories names, returns the last Folder in the provided Path
     */
    public static BaseFileSystemObject getFileSystemByAbsolutPath(List<String> subDir, Host host) throws FileNotExistsException,
            NotADirectoryException {
        if(subDir.get(0).isEmpty()) subDir.remove(0);
        BaseFileSystemObject directory = host.getRootFileSystem();
        try {
            for (String subDirectory: subDir) {
                directory = directory.getSpecificChildren(subDirectory);
                if(!(directory instanceof DirectoryObject)){
                    throw new NotADirectoryException(directory.getName());
                }
            }
            return directory;
        }
        catch (NullPointerException e)
        {
            throw new FileNotExistsException();
        }

    }

    public static List<String> pathToArray(String path){
        String[] subDirectories = path.split("/");
        return new ArrayList<>(Arrays.asList(subDirectories));
    }

    /**
     * Used to get the File / Directory with a Single Relative Path like
     * e.g. current Path is /etc, requested File is /etc/FileName
     * @param childrenName the Children File / Directory requested
     * @param host The host the children belongs to
     * @return The requested File / Directory, Returns NULL if the requested File / Directory does not exist
     * @throws FileNotExistsException
     */
    public static BaseFileSystemObject resolveSingleRelativePath(String childrenName, Host host) throws FileNotExistsException {
        return host.getCurrentDirectory().getSpecificChildren(childrenName);
    }

    /**
     * Used to get the File / Directory with a Multi Relative Path like
     * e.g. current Path is /etc, requested File is /etc/anotherDirectory/FileName
     * @param argList Relative Path as a List
     * @param host
     * @return The Requested File / Directory
     * @throws FileNotExistsException
     */
    public static BaseFileSystemObject resolveMultiRelativePath(List<String> argList, Host host) throws FileNotExistsException {
        String[] subDirectoriesStrings = argList.get(0).split("/");
        BaseFileSystemObject recursiveDir = host.getCurrentDirectory();
        for (String dirName: subDirectoriesStrings) {
            recursiveDir = recursiveDir.getSpecificChildren(dirName);
            if(recursiveDir == null) throw new FileNotExistsException();
        }
        return recursiveDir;
    }

}

