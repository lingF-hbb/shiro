package com.shiro.entity.mysql;

import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public class MUserTable{
    private int id;
    private String username;
    private String password;
    private int role_id;
    private Role role;
    private Permission permission;

    public MUserTable() {
    }

    public MUserTable(String username, String password, int role_id) {
        this.username = username;
        this.password = password;
        this.role_id = role_id;
    }

    public MUserTable(String username, String password, int role_id, Role role) {
        this.username = username;
        this.password = password;
        this.role_id = role_id;
        this.role = role;
    }

    public MUserTable(int id, String username, String password, int role_id, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role_id = role_id;
        this.role = role;
    }

    public MUserTable(int id, String username, String password, int role_id, Permission permission) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role_id = role_id;
        this.permission = permission;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    @Override
    public String toString() {
        return "MUserTable{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role_id=" + role_id +
                ", role=" + role +
                ", permission=" + permission +
                '}';
    }
}
