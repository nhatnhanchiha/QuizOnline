package com.bac.quizonline.model.mapper;

import com.bac.quizonline.model.entity.TakenSubjectDetail;

public interface TakenSubjectDetailMapper {
    int insert(TakenSubjectDetail record);

    int insertSelective(TakenSubjectDetail record);
}