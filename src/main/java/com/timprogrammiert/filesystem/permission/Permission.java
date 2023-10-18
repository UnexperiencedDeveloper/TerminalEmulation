package com.timprogrammiert.filesystem.permission;

/**
 * Represent the Persmissions for each File / Directory
 */
public class Permission {
    public static String DEFAULT_FILE_PERMISSION = "rw-rw-rw-";
    public static String DEFAULT_DIRECTORY_PERMISSION = "rwxrwxrwx";
    private String permissionString;
    private final User user;
    private final UserGroup userGroup;

    /**
     * Creates a new Permission Object, can be attached to a File / Directory Object
     * Permissions are represented like in linux. e.g. only user can read and write = rw-------
     * Directories must have the ability to execute, otherwise user is not allowed to cd in Directory
     * @param user The User who owns this permission
     * @param permissionString The permission String, can be defaulted via PermissionCreater
     */
    public Permission(User user, String permissionString){
        this.user = user;
        this.permissionString = permissionString;
        userGroup = user.getUserGroups().get(0); // Get Default Group, as this is the first one set
    }

    public String getPermissionString() {
        return permissionString;
    }

    public void setPermissionString(String permissionString) {
        this.permissionString = permissionString;
    }

    public User getUser() {
        return user;
    }

    public UserGroup getUserGroup() {
        return userGroup;
    }
}
