package com.bac.quizonline.model.mapper;

import com.bac.quizonline.model.entity.Role;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Short id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Short id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> getAllRole();
}