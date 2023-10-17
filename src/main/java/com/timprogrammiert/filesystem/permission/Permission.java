package com.timprogrammiert.filesystem.permission;

public class Permission {
    private String userPermission;
    private String groupPermission;
    private String otherPermission;

    public String getUserPermission(){
        return userPermission;
    }
    public String getGroupPermission(){
        return groupPermission;
    }
    public String getOtherPermission(){
        return otherPermission;
    }
    public void setUserPermission(String userPermission){
        this.userPermission = userPermission;
    }
    public void setGroupPermission(String groupPermission){
        this.groupPermission = groupPermission;
    }
    public void setOtherPermission(String otherPermission){
        this.otherPermission = otherPermission;
    }
}
