package com.shiro.entity.mysql;

import org.springframework.stereotype.Repository;

@Repository
public class Permission {
    private int id;
    private String permissionname;
    private int role_id;

    public Permission() {
    }

    public Permission(String permissionname, int role_id) {
        this.permissionname = permissionname;
        this.role_id = role_id;
    }

    public Permission(int id, String permissionname, int role_id) {
        this.id = id;
        this.permissionname = permissionname;
        this.role_id = role_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPermissionname() {
        return permissionname;
    }

    public void setPermissionname(String permissionname) {
        this.permissionname = permissionname;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", permissionname='" + permissionname + '\'' +
                ", role_id=" + role_id +
                '}';
    }
}
