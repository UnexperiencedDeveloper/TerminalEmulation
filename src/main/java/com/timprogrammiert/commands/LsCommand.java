package com.timprogrammiert.commands;

import com.timprogrammiert.exceptions.FileNotExistsException;
import com.timprogrammiert.exceptions.NotADirectoryException;
import com.timprogrammiert.filesystem.BaseFileSystemObject;
import com.timprogrammiert.filesystem.DirectoryObject;
import com.timprogrammiert.host.Host;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author tmatz
 */
public class LsCommand implements  ICommand{
    private boolean detailedList = false;
    private Host host;
    @Override
    public void execute(String[] args, Host userHost){
        host = userHost;
        try {
            List<String> argList = parseArgumentsForTags(new ArrayList<>(Arrays.asList(args)));

            if(argList.isEmpty()){
                listAllChildren(userHost.getCurrentDirectory());
            }  else if(argList.get(0).equals("/")){
                listAllChildren(userHost.getRootFileSystem());
            }
            else if(argList.get(0).startsWith("/")){
                resolveTargetDirectory(argList);
            } else {
                if(argList.get(0).split("/").length == 1) {
                    resolveSingleRelativePath(argList);
                }else {
                    resolveMultiRelativePath(argList);
                }
            }
        }
        catch (NotADirectoryException | FileNotExistsException e){
            System.out.println(e.getMessage());
        }

    }

    private void resolveTargetDirectory(List<String> argList) throws NotADirectoryException, FileNotExistsException {
        String absPath = argList.get(0);
        String[] subDirectoriesStrings =  absPath.split("/");
        List<String> subDirectories = new ArrayList<>(Arrays.asList(subDirectoriesStrings));
        listAllChildren(getFileSystemByAbsolutPath(subDirectories, host));
    }

    private void resolveSingleRelativePath(List<String> argList) throws NotADirectoryException, FileNotExistsException {
        listAllChildren(host.getCurrentDirectory().getSpecificChildren(argList.get(0)));
    }

    private void resolveMultiRelativePath(List<String> argList) throws NotADirectoryException, FileNotExistsException {
        String[] subDirectoriesStrings = argList.get(0).split("/");
        BaseFileSystemObject recursiveDir = host.getCurrentDirectory();
        for (String dirName: subDirectoriesStrings) {
            recursiveDir = recursiveDir.getSpecificChildren(dirName);
        }
        listAllChildren(recursiveDir);
    }

    private List<String> parseArgumentsForTags(List<String> argList){
        if(argList.contains("-al")){
            detailedList = true;
            argList.remove("-al");
        }
        return argList;
    }



    /**
     *
     * @param subDir List of subDirectories names
     * @return Requested Directory by absolute Path
     * Takes in a List of subDirectories names, returns the last Folder in the provided Path
     */
    private BaseFileSystemObject getFileSystemByAbsolutPath(List<String> subDir, Host host) throws FileNotExistsException,
            NotADirectoryException{
        if(subDir.get(0).isEmpty()) subDir.remove(0); // empty String on Position 0
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

    private void listAllChildren(BaseFileSystemObject baseItem) throws NotADirectoryException, FileNotExistsException {
        try {
            if(baseItem instanceof DirectoryObject)
            {
                Collection<BaseFileSystemObject> children = baseItem.getAllChilldren();
                for (BaseFileSystemObject object: children) {
                    printInformation(object.getName());
                }
            }else {
                throw new NotADirectoryException(baseItem.getName());
            }
        }catch (NullPointerException e){
            throw new FileNotExistsException();
        }


    }

    private void printInformation(String information){
        System.out.println(information);
    }
}
