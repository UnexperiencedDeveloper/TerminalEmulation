package com.timprogrammiert.commands.ls;

import com.timprogrammiert.commands.ICommand;
import com.timprogrammiert.exceptions.FileNotExistsException;
import com.timprogrammiert.filesystem.BaseFileSystemObject;
import com.timprogrammiert.filesystem.DirectoryObject;
import com.timprogrammiert.host.Host;
import com.timprogrammiert.util.DirectoryInfo;
import com.timprogrammiert.util.ErrorValues;

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
        List<String> argList = parseArgumentsForTags(new ArrayList<>(Arrays.asList(args)));

        try {
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
        }catch (FileNotExistsException e){
            printInformation(ErrorValues.FILE_NOT_EXIST);
        }
        catch (ClassCastException e){
            System.out.println("Wrong Type, ClassCastException");
        }

    }


    private void listCurrentDirectory() throws FileNotExistsException {
        listAllChildren(host.getCurrentDirectory());
    }
    private void listRootDirectory() throws FileNotExistsException {
        listAllChildren(host.getRootFileSystem());
    }
    private void resolveTargetDirectory(List<String> argList) throws FileNotExistsException, ClassCastException {
        List<String> subDirectories = DirectoryInfo.pathToArray(argList.get(0));
        listAllChildren(DirectoryInfo.getFileSystemByAbsolutPath(subDirectories, host, DirectoryObject.class));
    }
    private void resolveSingleRelativePath(List<String> argList) throws FileNotExistsException, ClassCastException {
        listAllChildren(DirectoryInfo.resolveSingleRelativePath(argList.get(0), host, DirectoryObject.class));
    }
    private void resolveMultiRelativePath(List<String> argList) throws FileNotExistsException, ClassCastException {
        listAllChildren(DirectoryInfo.resolveMultiRelativePath(argList.get(0), host, DirectoryObject.class));
    }

    private List<String> parseArgumentsForTags(List<String> argList){
        if(argList.contains("-al")){
            detailedList = true;
            argList.remove("-al");
        }
        return argList;
    }
    private void listAllChildren(BaseFileSystemObject baseItem) throws FileNotExistsException {
        try {
            if(baseItem instanceof DirectoryObject baseDirectory)
            {
                Collection<BaseFileSystemObject> children = baseDirectory.getAllChildren();
                for (BaseFileSystemObject object: children) {
                    printInformation(object.getName());
                }
            }else {
                printInformation(baseItem.getName() + ErrorValues.NOT_A_DIRECTORY);
            }
        }catch (NullPointerException e){
            throw new FileNotExistsException();
        }
    }

    private void printInformation(String information){
        System.out.println(information);
    }
}
