package com.timprogrammiert.commands;

import com.timprogrammiert.filesystem.BaseFileSystemObject;
import com.timprogrammiert.filesystem.DirectoryObject;
import com.timprogrammiert.host.Host;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author tmatz
 */
public class LsCommand implements  ICommand{
    @Override
    public void execute(String[] args, Host userHost){
        boolean detailedList = false;
        List<String> argList = new ArrayList<>(Arrays.asList(args));

        if(argList.contains("-al")){
            detailedList = true;
            argList.remove("-al");
        }

        if(argList.isEmpty()){
            // list current directory
            listAllChildren(userHost.getCurrentDirectory());
        }  else if(argList.get(0).equals("/")){
            // list root directory
            listAllChildren(userHost.getRootFileSystem());
        }

        else if(argList.get(0).startsWith("/")){
            // list absolute path directory
            String absPath = argList.get(0);
            String[] subDirectoriesStrings =  absPath.split("/");
            List<String> subDirectories = new ArrayList<>(Arrays.asList(subDirectoriesStrings));
            listAllChildren(getFileSystemByAbsolutPath(subDirectories, userHost));
        } else {
            // list relative path
            listAllChildren(userHost.getCurrentDirectory().getSpecificChildren(argList.get(0)));

        }
    }

    /**
     *
     * @param subDir List of subDirectories names
     * @return Requested Directory by absolute Path
     * Takes in a List of subDirectories names, returns the last Folder in the provided Path
     */
    private BaseFileSystemObject getFileSystemByAbsolutPath(List<String> subDir, Host host){
        // TODO CHECK IF IS DIRECTORY OR FILE (CHECK ALREADY AVAILABLE, JUST USE THE INFORMATION)
        subDir.remove(0); // empty String on Position 0
        BaseFileSystemObject directory = host.getRootFileSystem();
        for (String subDirectory: subDir) {
            directory = directory.getSpecificChildren(subDirectory);

        }
        return directory;
    }

    private void listAllChildren(BaseFileSystemObject baseItem){
        if(baseItem instanceof DirectoryObject)
        {
            Collection<BaseFileSystemObject> children = baseItem.getAllChilldren();
            for (BaseFileSystemObject object: children) {
                System.out.println(object.getName());
            }
        }else {
            System.out.println(baseItem.getName() + " is a file");
        }

    }
}
