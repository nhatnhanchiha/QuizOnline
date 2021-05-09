package com.bac.quizonline.model.mapper;

import com.bac.quizonline.model.entity.Question;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QuestionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Question record);

    int insertSelective(Question record);

    Question selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Question record);

    int updateByPrimaryKey(Question record);

    int updateStatus(Question record);

    Question selectWithUser(Question record);

    List<Question> selectAll(@Param("question") Question record, @Param("offset") int offset, @Param("limit") int limit);

    List<Question> getQuestionsForQuiz(@Param("idSubject") int idSubject, @Param("begin") int begin, @Param("end") int end);
}