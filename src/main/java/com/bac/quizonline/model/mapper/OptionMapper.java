package com.bac.quizonline.model.mapper;

import com.bac.quizonline.model.entity.Option;

public interface OptionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Option record);

    int insertSelective(Option record);

    Option selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Option record);

    int updateByPrimaryKey(Option record);
}