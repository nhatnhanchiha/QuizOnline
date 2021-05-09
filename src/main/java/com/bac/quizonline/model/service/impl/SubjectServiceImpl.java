package com.bac.quizonline.model.service.impl;

import com.bac.quizonline.model.entity.Option;
import com.bac.quizonline.model.entity.Question;
import com.bac.quizonline.model.entity.Subject;
import com.bac.quizonline.model.mapper.OptionMapper;
import com.bac.quizonline.model.mapper.QuestionMapper;
import com.bac.quizonline.model.mapper.SubjectMapper;
import com.bac.quizonline.model.service.SubjectService;
import com.bac.quizonline.model.utilities.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class SubjectServiceImpl implements SubjectService {
    @Override
    public List<Subject> getAllSubject(Subject record, int offset, int limit) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            final SubjectMapper subjectMapper = session.getMapper(SubjectMapper.class);
            final List<Subject> allSubject = subjectMapper.getAllSubject(record, offset, limit);
            final List<Subject> result = new ArrayList<>();
            for (Subject subject : allSubject) {
                if (subjectMapper.countValidQuestion(subject.getId()) >= subject.getNumberOfQuestionInQuiz()) {
                    result.add(subject);
                }
            }
            return result;
        }
    }

    @Override
    public List<Subject> getAllCreatedSubjectByIdUser(String idUser) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            final SubjectMapper subjectMapper = session.getMapper(SubjectMapper.class);
            final Subject subject = Subject.SubjectBuilder
                    .aSubject()
                    .withIdUser(idUser)
                    .build();
            return subjectMapper.getAllCreatedSubject(subject);
        }
    }

    @Override
    public int addQuestion(Subject subject) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            SubjectMapper subjectMapper = session.getMapper(SubjectMapper.class);
            Subject subjectInDb = subjectMapper.selectByPrimaryKey(subject.getId());
            if (subjectInDb == null) {
                session.rollback();
                return HttpServletResponse.SC_NOT_FOUND;
            }

            if (!subjectInDb.getIdUser().equals(subject.getIdUser())) {
                session.rollback();
                return HttpServletResponse.SC_NOT_FOUND;
            }

            QuestionMapper questionMapper = session.getMapper(QuestionMapper.class);
            OptionMapper optionMapper = session.getMapper(OptionMapper.class);

            for (Question question : subject.getQuestions()) {
                question.setIdSubject(subject.getId());
                int result = questionMapper.insert(question);
                if (result == 0) {
                    session.rollback();
                    return HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
                }

                List<Option> options = question.getOptions();
                for (Option option : options) {
                    option.setIdQuestion(question.getId());
                    result = optionMapper.insert(option);
                    if (result == 0) {
                        session.rollback();
                        return HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
                    }
                }
            }
            session.commit();
        }
        return HttpServletResponse.SC_OK;
    }

    @Override
    public Subject getValidSubject(int idSubject) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            final SubjectMapper subjectMapper = session.getMapper(SubjectMapper.class);
            Subject subject = Subject.SubjectBuilder
                    .aSubject()
                    .withId(idSubject)
                    .build();
            subject = subjectMapper.selectOne(subject);
            if (subject == null || !subject.getStatus()) {
                return null;
            }

            final int numOfValidQuestion = subjectMapper.countValidQuestion(idSubject);

            if (numOfValidQuestion < subject.getNumberOfQuestionInQuiz()) {
                return null;
            }

            subject.setTotalOfValidQuestion(numOfValidQuestion);

            return subject;
        }
    }


}
