package com.shiro.service.mysql.impl;

import com.shiro.entity.mysql.MUserTable;
import com.shiro.mapper.mysql.MUserTableMapper;
import com.shiro.service.mysql.IMUserTableMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class MUserTableMapperImpl implements IMUserTableMapper {

    @Autowired
    private MUserTableMapper mUserTableMapper;


    @Override
    public List<MUserTable> selectAll() {
        return mUserTableMapper.selectAll();
    }

    @Override
    public MUserTable selectAllByUsername(String username) {
        return mUserTableMapper.selectAllByUsername(username);
    }

    @Override
    public Set<String> selectRoleByName(String username) {
        return mUserTableMapper.selectRoleByName(username);
    }

    @Override
    public Set<String> selectPermissionByName(String username) {
        return mUserTableMapper.selectPermissionByName(username);
    }

    @Override
    public int insertNewAuth(MUserTable mUserTable) {
        return mUserTableMapper.insertNewAuth(mUserTable);
    }


}
