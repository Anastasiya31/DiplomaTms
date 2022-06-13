package com.example.diplom.enums;

public enum Permission {

    USER_READ(""),
    USER_WRITE("");

        private final String permission;

        Permission(String permission){
            this.permission = permission;
        }

    public String getPermissions(){
        return permission;
    }
}
