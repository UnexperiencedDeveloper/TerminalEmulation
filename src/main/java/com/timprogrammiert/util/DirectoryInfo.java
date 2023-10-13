package com.timprogrammiert.util;

import com.timprogrammiert.filesystem.BaseFileSystemObject;
import com.timprogrammiert.filesystem.DirectoryObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author tmatz
 */
public class DirectoryInfo {
    public static String getAbsolutPath(BaseFileSystemObject fileSystemObject){
        // Go to root
        BaseFileSystemObject parent = fileSystemObject;
        List<String> allParents = new ArrayList<>();
        while (parent.getParent() != null){
            parent = parent.getParent();
            allParents.add(parent.getParent().getName());
        }
        List<String> correctOrder = new ArrayList<>(allParents);
        Collections.reverse(correctOrder);

        StringBuilder output = new StringBuilder("/");
        for (String dirName: correctOrder) {
            output.append(dirName).append("/");
        }
        return output.toString();
    }
}
