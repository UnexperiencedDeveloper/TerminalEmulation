package com.timprogrammiert.util;

import com.timprogrammiert.filesystem.BaseFileSystemObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author tmatz
 */
public class DirectoryInfo {
    public static String getAbsolutPath(BaseFileSystemObject fileSystemObject){
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

}

