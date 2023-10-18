package com.timprogrammiert.commands.cd;

import com.timprogrammiert.commands.ICommand;
import com.timprogrammiert.exceptions.FileNotExistsException;
import com.timprogrammiert.filesystem.BaseFileSystemObject;
import com.timprogrammiert.filesystem.DirectoryObject;
import com.timprogrammiert.filesystem.util.DirectoryUtil;
import com.timprogrammiert.filesystem.util.PermissionChecker;
import com.timprogrammiert.host.Host;
import com.timprogrammiert.util.ErrorValues;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author tmatz
 */
public class CdCommand implements ICommand {

    private Host host;
    @Override
    public void execute(String[] args, Host host) {
        this.host = host;
        List<String> argList = new ArrayList<>(Arrays.asList(args));

        try {
            if(argList.isEmpty()){
                // do nothing ? Maybe Error
            }  else if(argList.get(0).equals("..")){
                resolveParentDirectory();
            }else if(argList.get(0).equals("/")){
                resolveRootDirectory();
            }
            else if(argList.get(0).startsWith("/")){
                resolveAbsolutePath(argList);
            } else {
                if(argList.get(0).split("/").length == 1) {
                    resolveSingleRelativePath(argList);
                }else {
                    resolveMultiRelativePath(argList);
                }
            }
        }catch (FileNotExistsException e){
            System.out.println(ErrorValues.FILE_NOT_EXIST);
        }
        catch (ClassCastException e){
            System.out.println(ErrorValues.NOT_A_DIRECTORY);
        }

    }
    private void resolveParentDirectory(){
        if(host.getCurrentDirectory().getParent() != null){
            BaseFileSystemObject resolvedDirectory =  host.getCurrentDirectory().getParent();
            changeDirectory(resolvedDirectory);
        }
    }
    private void resolveRootDirectory(){
        changeDirectory(host.getRootFileSystem());
    }
    private void resolveSingleRelativePath(List<String> path) throws FileNotExistsException {
        BaseFileSystemObject resolvedDirectory = DirectoryUtil.resolveSingleRelativePath(
                path.get(0), host, DirectoryObject.class);
        changeDirectory(resolvedDirectory);

    }
    private void resolveMultiRelativePath(List<String> path) throws FileNotExistsException {
        BaseFileSystemObject resolvedDirectory = DirectoryUtil.resolveMultiRelativePath(
                DirectoryUtil.pathToArray(path.get(0)), host, DirectoryObject.class);
        changeDirectory(resolvedDirectory);
    }
    private void resolveAbsolutePath(List<String> path) throws FileNotExistsException {
        List<String> subDirectories = DirectoryUtil.pathToArray(path.get(0));
        BaseFileSystemObject resolvedDirectory = DirectoryUtil.getFileSystemByAbsolutPath(
                subDirectories, host, DirectoryObject.class);
        changeDirectory(resolvedDirectory);
    }

    private void changeDirectory(BaseFileSystemObject directoryToCd){
        PermissionChecker pemChecker = new PermissionChecker(directoryToCd, host.getCurrentUser());
        // Directory needs Permission to execute
        if(pemChecker.canExecute){
            host.setCurrentDirectory(directoryToCd);
        }

    }
}
