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

    private static Host host;
    public static void execute(String[] args, Host userHost){
        host = userHost;
        // ls testfile -> children aus aktuellen directory, dessen children anzeigen
        // ls /test/etc/testfile -> absoluter pfad, childen anzeigen
        // ls -al {beide varianten} -> alles zeige + aufgelistet
        if(args[0].startsWith("/")){
            // Absoluter pfad
            String absPath = args[0];
            String[] subDirectoriesStrings =  absPath.split("/");
            List<String> subDirectories = new ArrayList<>(Arrays.asList(subDirectoriesStrings));
            test(getFileSystemByAbsolutPath(subDirectories));
        }

    }

    /**
     *
     * @param subDir
     * @return Requested Directory by absolute Path
     */
    private static BaseFileSystemObject getFileSystemByAbsolutPath(List<String> subDir){
        // TODO CHECK IF IS DIRECTORY OR FILE (CHECK ALREADY AVAILABLE, JUST USE THE INFORMATION)
        subDir.remove(0);
        BaseFileSystemObject directory = host.getRootFileSystem();
        for (String subDirectory: subDir) {
            directory = directory.getSpecificChildren("etc");
            System.out.println(directory.getName());
        }
        return directory;
    }

    // Irelevant -> just for Testing Logic
    private static void test(BaseFileSystemObject testItem){
        Collection<BaseFileSystemObject> children = testItem.getAllChilldren();
        for (BaseFileSystemObject object: children) {
            System.out.println(object.getName());
        }
    }


    private static void relativePath(){

    }


}
