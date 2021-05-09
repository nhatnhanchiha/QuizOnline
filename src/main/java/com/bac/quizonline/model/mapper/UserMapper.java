package com.bac.quizonline.model.mapper;

import com.bac.quizonline.model.entity.User;

public interface UserMapper {
    int deleteByPrimaryKey(String email);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String email);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User login(User record);

    User checkExist(String email);

}