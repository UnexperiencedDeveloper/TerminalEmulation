package com.timprogrammiert.commands.ls;

import com.timprogrammiert.commands.ICommand;
import com.timprogrammiert.exceptions.FileNotExistsException;
import com.timprogrammiert.exceptions.NotADirectoryException;
import com.timprogrammiert.filesystem.BaseFileSystemObject;
import com.timprogrammiert.filesystem.DirectoryObject;
import com.timprogrammiert.host.Host;
import com.timprogrammiert.util.DirectoryInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author tmatz
 */
public class LsCommand implements ICommand {
    private boolean detailedList = false;
    private Host host;
    @Override
    public void execute(String[] args, Host userHost){
        host = userHost;
        try {
            List<String> argList = parseArgumentsForTags(new ArrayList<>(Arrays.asList(args)));

            if(argList.isEmpty()){
                listCurrentDirectory();
            }  else if(argList.get(0).equals("/")){
                listRootDirectory();
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


    private void listCurrentDirectory() throws NotADirectoryException, FileNotExistsException {
        listAllChildren(host.getCurrentDirectory());
    }
    private void listRootDirectory() throws NotADirectoryException, FileNotExistsException {
        listAllChildren(host.getRootFileSystem());
    }
    private void resolveTargetDirectory(List<String> argList) throws NotADirectoryException, FileNotExistsException {
        List<String> subDirectories = DirectoryInfo.pathToArray(argList.get(0));
        listAllChildren(DirectoryInfo.getFileSystemByAbsolutPath(subDirectories, host));
    }
    private void resolveSingleRelativePath(List<String> argList) throws NotADirectoryException, FileNotExistsException {
        listAllChildren(DirectoryInfo.resolveSingleRelativePath(argList.get(0), host));
    }

    /**
     * Used to list Files with a Relative Path of multiple Directories
     */
    private void resolveMultiRelativePath(List<String> argList) throws NotADirectoryException, FileNotExistsException {

        listAllChildren(DirectoryInfo.resolveMultiRelativePath(argList, host));
    }

    private List<String> parseArgumentsForTags(List<String> argList){
        if(argList.contains("-al")){
            detailedList = true;
            argList.remove("-al");
        }
        return argList;
    }
    private void listAllChildren(BaseFileSystemObject baseItem) throws NotADirectoryException, FileNotExistsException {
        try {
            if(baseItem instanceof DirectoryObject baseDirectory)
            {
                Collection<BaseFileSystemObject> children = baseDirectory.getAllChilldren();
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
