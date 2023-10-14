package com.timprogrammiert.commands;

import com.timprogrammiert.filesystem.BaseFileSystemObject;
import com.timprogrammiert.host.Host;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author tmatz
 */
public class LsCommand{
    public static void execute(String[] args, Host userHost){
        if(args.length == 0){
            listAllChildren(userHost.getCurrentDirectory());
        }
        else if(args[0].equals("/")){
            // LS COMMAND ON ROOT DIRECTORY
            listAllChildren(userHost.getRootFileSystem());
        }

        else if(args[0].startsWith("/")){
            // Absoluter pfad
            String absPath = args[0];
            String[] subDirectoriesStrings =  absPath.split("/");
            List<String> subDirectories = new ArrayList<>(Arrays.asList(subDirectoriesStrings));
            listAllChildren(getFileSystemByAbsolutPath(subDirectories, userHost));
        }

    }

    /**
     *
     * @param subDir List of subDirectories names
     * @return Requested Directory by absolute Path
     * Takes in a List of subDirectories names, returns the last Folder in the provided Path
     */
    private static BaseFileSystemObject getFileSystemByAbsolutPath(List<String> subDir, Host host){
        // TODO CHECK IF IS DIRECTORY OR FILE (CHECK ALREADY AVAILABLE, JUST USE THE INFORMATION)
        subDir.remove(0); // empty String on Position 0
        BaseFileSystemObject directory = host.getRootFileSystem();
        for (String subDirectory: subDir) {
            directory = directory.getSpecificChildren(subDirectory);

        }
        return directory;
    }

    private static void listAllChildren(BaseFileSystemObject testItem){
        Collection<BaseFileSystemObject> children = testItem.getAllChilldren();
        for (BaseFileSystemObject object: children) {
            System.out.println(object.getName());
        }
    }


    private static void relativePath(){

    }


}
