package com.bac.quizonline.model.mapper;

import com.bac.quizonline.model.entity.TakenSubject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TakenSubjectMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TakenSubject record);

    int insertSelective(TakenSubject record);

    TakenSubject selectByPrimaryKey(Integer id);

    TakenSubject selectByIdSubjectAndIdUser(@Param("idSubject") int idSubject, @Param("idUser") String idUser);

    TakenSubject getFullDetail(@Param("id") Integer id, @Param("offset") int offset, @Param("limit") int limit);

    TakenSubject getValidTakenSubject(@Param("id") int id, @Param("idUser") String idUser);

    List<TakenSubject> selectHistoryByIdUser(@Param("idUser") String email, @Param("nameOrIdSubject") String nameOrIdSubject,@Param("orIdSubject") Integer orIdSubject, @Param("offset") int offset, @Param("limit") int limit);

    int endTakenSubject(@Param("takenSubject") TakenSubject takenSubject);

    int updateByPrimaryKeySelective(TakenSubject record);

    int updateByPrimaryKey(TakenSubject record);

    TakenSubject getHistory(@Param("id") int id);
}