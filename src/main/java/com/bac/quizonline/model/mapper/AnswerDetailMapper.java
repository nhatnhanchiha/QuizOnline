package com.bac.quizonline.model.mapper;

import com.bac.quizonline.model.entity.AnswerDetail;
import org.apache.ibatis.annotations.Param;

public interface AnswerDetailMapper {
    int deleteByPrimaryKey(@Param("idTakenSubject") Integer idTakenSubject, @Param("idQuestion") Integer idQuestion, @Param("idOption") Integer idOption);

    int deleteByIdTakenSubjectAndIdQuestion(@Param("idTakenSubject") Integer idTakenSubject, @Param("idQuestion") Integer idQuestion);

    AnswerDetail queryByPrimaryKey(@Param("idTakenSubject") Integer idTakenSubject, @Param("idQuestion") Integer idQuestion, @Param("idOption") Integer idOption);

    int insert(AnswerDetail record);

    int insertSelective(AnswerDetail record);
}