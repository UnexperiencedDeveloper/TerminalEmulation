package com.timprogrammiert.filesystem.util;

import com.timprogrammiert.exceptions.FileNotExistsException;
import com.timprogrammiert.filesystem.BaseFileSystemObject;
import com.timprogrammiert.host.Host;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author tmatz
 */
public class DirectoryUtil {
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
     * The Absolut Path is splitted into Arrays (use DirectoryInfo.PathToArray) and parsed until the last element of
     * the array, which represents the Target rather it be a File or Directory it gets Returned.
     * Specifiy the Object you want to get as childType
     * If the Object is not found a null Object is returned
     * @param subDir AbsolutPath splitted via DirectoryInfo.PathToArray
     * @param host The host the filesystem belongs to
     * @param childType DirectoryObject.class or FileObject.class
     * @return If the found Object is an Instance of the given Class then it will be returned, otherwise
     * an Excpetion ClassCastException will be thrown
     * @param <T> DirecotyObject.class or FileObject.class
     * @throws FileNotExistsException Thrown by BaseFileSystemObject when the Object is null
     * @throws ClassCastException If the found Object is not an Instance of the given Class
     */
    public static <T extends BaseFileSystemObject> T getFileSystemByAbsolutPath(List<String> subDir, Host host, Class<T> childType) throws
            ClassCastException, FileNotExistsException {
        if(subDir.get(0).isEmpty()) subDir.remove(0);
        BaseFileSystemObject objectToSearch = host.getRootFileSystem();

        for (String subDirectory: subDir) {
            objectToSearch = objectToSearch.getSpecificChildren(subDirectory);
        }
        if(childType.isInstance(objectToSearch)){
            return childType.cast(objectToSearch);
        }else{
            throw new ClassCastException();
        }

    }

    public static List<String> pathToArray(String path){
        String[] subDirectories = path.split("/");
        return new ArrayList<>(Arrays.asList(subDirectories));
    }


    /**
     * Search for the specified children in currentDirectory (gained by host)
     * @param childrenName The name of the Children you want to recieve
     * @param host The host the Filesystem belongs to
     * @param childType The Type the Children belongs to (DirectoryObject, FileObject)
     * @return The Children, if its an Instance of specified Class
     * @throws FileNotExistsException Is thrown by BaseFileSystemObject when the Object is Null
     * @throws ClassCastException Is thrown when the Specified Class does not match with the Found Object
     */
    public static <T extends BaseFileSystemObject> T  resolveSingleRelativePath(String childrenName, Host host, Class<T> childType)
            throws FileNotExistsException, ClassCastException {

        if(childType.isInstance(host.getCurrentDirectory().getSpecificChildren(childrenName))){
            return childType.cast(host.getCurrentDirectory().getSpecificChildren(childrenName));
        }else {
            throw new ClassCastException();
        }
    }

    /**
     * The PathString gets converted into an Array, splitted by "/" as SubDirectory
     * Each subDirectory is searched in the Children List of the current looped Directory
     * @param pathString the relative Path to the Object (e.g path/to/file)
     * @param host The Host the Filesystem belongs to
     * @param childType The Type the Children belongs to (DirectoryObject, FileObject)
     * @return The Children, if its an Instance of the specified Class
     * @throws FileNotExistsException Is thrown by BaseFileSystemObject when the Object is Null
     * @throws ClassCastException Is thrown when the Specified Class does not match with the Found Object
     */
    public static <T extends BaseFileSystemObject> T resolveMultiRelativePath(String pathString, Host host, Class<T> childType)
            throws FileNotExistsException, ClassCastException {
        String[] subDirectoriesStrings = pathString.split("/");
        BaseFileSystemObject recursiveDir = host.getCurrentDirectory();
        for (String dirName: subDirectoriesStrings) {
            recursiveDir = recursiveDir.getSpecificChildren(dirName);
        }
        if(childType.isInstance(recursiveDir)){
            return childType.cast(recursiveDir);
        }else {
          throw new ClassCastException();
        }
    }

}

