package com.bac.quizonline.model.mapper;

import com.bac.quizonline.model.entity.Subject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SubjectMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Subject record);

    int insertSelective(Subject record);

    Subject selectByPrimaryKey(Integer id);

    Subject selectOne(Subject record);

    int updateByPrimaryKeySelective(Subject record);

    int updateByPrimaryKey(Subject record);

    List<Subject> getAllSubject(@Param("subject") Subject subject, @Param("offset") int offset, @Param("limit") int limit);

    List<Subject> getAllCreatedSubject(Subject record);

    int countValidQuestion(int idSubject);
}