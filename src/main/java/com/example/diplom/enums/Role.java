package com.example.diplom.enums;

import java.util.Set;

public enum Role {
    USER(Set.of(Permission.USER_READ)),
    ADMIN(Set.of(Permission.USER_READ, Permission.USER_WRITE));

    private Set<Permission> permissions;
    Role(Set<Permission> permissions){
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions(){
        return this.permissions;
    }

}
