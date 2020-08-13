package com.shiro.service.mysql;

import com.shiro.entity.mysql.MUserTable;

import java.util.List;
import java.util.Set;

public interface IMUserTableMapper {

    public List<MUserTable> selectAll();

    MUserTable selectAllByUsername(String username);

    Set<String> selectRoleByName(String username);

    Set<String> selectPermissionByName(String username);

    int insertNewAuth(MUserTable mUserTable);
}
