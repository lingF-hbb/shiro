package com.shiro.entity.mysql;

import org.springframework.stereotype.Repository;

@Repository
public class Role {
    private int id;
    private String rolename;

    public Role() {
    }

    public Role(int id) {
        this.id = id;
    }

    public Role(int id, String rolename) {
        this.id = id;
        this.rolename = rolename;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", rolename='" + rolename + '\'' +
                '}';
    }
}
