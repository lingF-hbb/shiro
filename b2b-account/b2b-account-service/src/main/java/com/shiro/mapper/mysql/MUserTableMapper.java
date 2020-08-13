package com.shiro.mapper.mysql;

import com.shiro.entity.mysql.MUserTable;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Mapper
@Repository
public interface MUserTableMapper {

     List<MUserTable> selectAll();

     MUserTable selectAllByUsername(String username);

     Set<String> selectRoleByName(String username);

     Set<String> selectPermissionByName(String username);

     int insertNewAuth(MUserTable mUserTable);
}
