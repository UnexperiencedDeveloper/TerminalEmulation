package com.timprogrammiert.filesystem.util;

import com.timprogrammiert.filesystem.BaseFileSystemObject;
import com.timprogrammiert.filesystem.permission.User;

public class PermissionChecker {
    private final BaseFileSystemObject baseFileSystemObject;
    private final User currentUser;
    public boolean canRead;
    public boolean canWrite;
    public boolean canExecute;
    public PermissionChecker(BaseFileSystemObject baseFileSystemObject, User currentUser) {
        this.baseFileSystemObject = baseFileSystemObject;
        this.currentUser = currentUser;
        extractPermissions();
    }

    private void extractPermissions(){
        String permissionString = baseFileSystemObject.getPermissions().getPermissionString();
        String ownerPermission = permissionString.substring(0,3);
        String groupPermission = permissionString.substring(3,6);
        String otherPermission = permissionString.substring(6,9);

        if(currentUser.equals(baseFileSystemObject.getPermissions().getUser())){
            // User is owner
            setPermissions(ownerPermission);
        } else if (currentUser.getUserGroups().contains(baseFileSystemObject.getPermissions().getUserGroup())) {
            // User is part of File owning Group
            setPermissions(groupPermission);
        } else {
            setPermissions(otherPermission);
        }

    }

    private void setPermissions(String permissions){
        canRead = permissions.charAt(0) == 'r';
        canWrite = permissions.charAt(1) == 'w';
        canExecute = permissions.charAt(2) == 'x';
    }
}
