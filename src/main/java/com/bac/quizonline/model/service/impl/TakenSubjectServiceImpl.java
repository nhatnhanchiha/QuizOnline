package com.bac.quizonline.model.service.impl;

import com.bac.quizonline.model.entity.Question;
import com.bac.quizonline.model.entity.TakenSubject;
import com.bac.quizonline.model.entity.TakenSubjectDetail;
import com.bac.quizonline.model.mapper.TakenSubjectDetailMapper;
import com.bac.quizonline.model.mapper.TakenSubjectMapper;
import com.bac.quizonline.model.service.TakenSubjectService;
import com.bac.quizonline.model.utilities.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

public class TakenSubjectServiceImpl implements TakenSubjectService {
    @Override
    public int insert(TakenSubject takenSubject) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            final TakenSubjectMapper takenSubjectMapper = session.getMapper(TakenSubjectMapper.class);
            final int result = takenSubjectMapper.insertSelective(takenSubject);
            if (result != 1) {
                session.rollback();
                return 0;
            }

            final TakenSubjectDetailMapper takenSubjectDetailMapper = session.getMapper(TakenSubjectDetailMapper.class);
            for (Question question : takenSubject.getSubject().getQuestions()) {
                final TakenSubjectDetail detail = TakenSubjectDetail.TakenSubjectDetailBuilder.aTakenSubjectDetail()
                        .withIdTakenSubject(takenSubject.getId())
                        .withIdQuestion(question.getId())
                        .build();
                final int insert = takenSubjectDetailMapper.insert(detail);
                if (insert != 1) {
                    session.rollback();
                    return 0;
                }
            }

            final long currentTimeMillis = System.currentTimeMillis();
            takenSubject.setStartTime(Instant.ofEpochMilli(currentTimeMillis).atZone(ZoneId.systemDefault()).toLocalDateTime());
            takenSubject.setEndTime(Instant.ofEpochMilli((long) (currentTimeMillis + ((takenSubject.getSubject().getMinutes()) * 60 * 1000))).atZone(ZoneId.systemDefault()).toLocalDateTime());

            final int o = takenSubjectMapper.updateByPrimaryKeySelective(
                    TakenSubject.TakenSubjectBuilder
                            .aTakenSubject()
                            .withId(takenSubject.getId())
                            .withStartTime(takenSubject.getStartTime())
                            .withEndTime(takenSubject.getEndTime())
                            .build()
            );
            if (o != 1) {
                session.rollback();
                return 0;
            }

            session.commit();
            return result;
        }
    }

    @Override
    public TakenSubject getTakenSubjectInQuiz(TakenSubject takenSubject) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            final TakenSubjectMapper takenSubjectMapper = session.getMapper(TakenSubjectMapper.class);
            final TakenSubject takenSubject1 = takenSubjectMapper.selectByIdSubjectAndIdUser(takenSubject.getIdSubject(), takenSubject.getIdUser());
            if (takenSubject1 != null && LocalDateTime.now().minusSeconds(10).compareTo(takenSubject1.getEndTime()) < 0 ) {
                return takenSubject1;
            }
        }
        return null;
    }

    @Override
    public TakenSubject getTakenSubjectWithDetail(TakenSubject takenSubject) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            final TakenSubjectMapper takenSubjectMapper = session.getMapper(TakenSubjectMapper.class);
            return takenSubjectMapper.getHistory(takenSubject.getId());
        }
    }

    @Override
    public TakenSubject getTakenSubjectWithDetailInProgressQuiz(TakenSubject takenSubject, int offset, int limit) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            final TakenSubjectMapper takenSubjectMapper = session.getMapper(TakenSubjectMapper.class);
            return takenSubjectMapper.getFullDetail(takenSubject.getId(), offset, limit);
        }
    }

    @Override
    public TakenSubject checkValidTakenSubjectId(int id, String idUser) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            final TakenSubjectMapper takenSubjectMapper = session.getMapper(TakenSubjectMapper.class);
            return takenSubjectMapper.getValidTakenSubject(id, idUser);
        }
    }

    @Override
    public List<TakenSubject> getTakenSubjectHistoryList(String email, String nameOrIdSubject, Integer orIdSubject, int offset, int limit) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            final TakenSubjectMapper takenSubjectMapper = session.getMapper(TakenSubjectMapper.class);
            return takenSubjectMapper.selectHistoryByIdUser(email, nameOrIdSubject, orIdSubject, offset, limit);
        }
    }

    @Override
    public int endTakenSubject(int id) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            final TakenSubjectMapper takenSubjectMapper = session.getMapper(TakenSubjectMapper.class);
            final TakenSubject takenSubject = TakenSubject.TakenSubjectBuilder
                    .aTakenSubject()
                    .withId(id)
                    .withEndTime(LocalDateTime.now()).build();
            takenSubjectMapper.endTakenSubject(takenSubject);
            session.commit();
        }
        return 0;
    }
}
