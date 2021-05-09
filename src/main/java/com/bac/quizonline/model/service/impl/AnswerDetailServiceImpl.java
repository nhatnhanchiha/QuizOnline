package com.bac.quizonline.model.service.impl;

import com.bac.quizonline.model.entity.AnswerDetail;
import com.bac.quizonline.model.mapper.AnswerDetailMapper;
import com.bac.quizonline.model.service.AnswerDetailService;
import com.bac.quizonline.model.utilities.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class AnswerDetailServiceImpl implements AnswerDetailService {
    @Override
    public int insertList(List<AnswerDetail> answerDetails) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            final AnswerDetailMapper answerDetailMapper = session.getMapper(AnswerDetailMapper.class);
            for (AnswerDetail answerDetail : answerDetails) {
                answerDetailMapper.deleteByIdTakenSubjectAndIdQuestion(answerDetail.getIdTakenSubject(), answerDetail.getIdQuestion());
            }
            for (AnswerDetail answerDetail : answerDetails) {
                answerDetailMapper.insert(answerDetail);
            }
            session.commit();
        }
        return 1;
    }
}
