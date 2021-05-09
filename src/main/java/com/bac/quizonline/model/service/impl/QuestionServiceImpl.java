package com.bac.quizonline.model.service.impl;

import com.bac.quizonline.model.entity.Option;
import com.bac.quizonline.model.entity.Question;
import com.bac.quizonline.model.entity.Subject;
import com.bac.quizonline.model.mapper.OptionMapper;
import com.bac.quizonline.model.mapper.QuestionMapper;
import com.bac.quizonline.model.mapper.SubjectMapper;
import com.bac.quizonline.model.service.QuestionService;
import com.bac.quizonline.model.utilities.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;

public class QuestionServiceImpl implements QuestionService {
    @Override
    public List<Question> getAllQuestions(Question question, int offset, int limit) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            final QuestionMapper questionMapper = session.getMapper(QuestionMapper.class);
            return questionMapper.selectAll(question, offset, limit);
        }
    }

    @Override
    public int deleteQuestion(Question question) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            final QuestionMapper questionMapper = session.getMapper(QuestionMapper.class);
            final Question questionInDb = questionMapper.selectWithUser(question);
            if (questionInDb == null || !questionInDb.getStatus()) {
                return HttpServletResponse.SC_OK;
            }

            question.setStatus(false);
            final int result = questionMapper.updateStatus(question);
            if (result == 1) {
                session.commit();
                return HttpServletResponse.SC_OK;
            } else {
                session.rollback();
                return HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
            }
        }
    }

    @Override
    public int updateQuestion(Question question) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            final SubjectMapper subjectMapper = session.getMapper(SubjectMapper.class);
            final Subject subjectInDb = subjectMapper.selectByPrimaryKey(question.getIdSubject());
            if (!subjectInDb.getIdUser().equals(question.getSubject().getIdUser())) {
                session.rollback();
                return HttpServletResponse.SC_BAD_REQUEST;
            }

            final List<Subject> subject = subjectMapper.getAllCreatedSubject(question.getSubject());
            if (subject == null) {
                session.rollback();
                return HttpServletResponse.SC_BAD_REQUEST;
            }

            final QuestionMapper questionMapper = session.getMapper(QuestionMapper.class);
            final int questionUpdateResult = questionMapper.updateByPrimaryKeySelective(question);
            if (questionUpdateResult != 1) {
                session.rollback();
                return 0;
            }

            final OptionMapper optionMapper = session.getMapper(OptionMapper.class);
            for (Option option : question.getOptions()) {
                if (option.getId() != null) {
                    final int optionUpdateResult = optionMapper.updateByPrimaryKeySelective(option);
                    if (optionUpdateResult != 1) {
                        session.rollback();
                        return HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
                    }
                } else {
                    final int optionInsertResult = optionMapper.insertSelective(option);
                    if (optionInsertResult != 1) {
                        session.rollback();
                        return HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
                    }
                }
            }

            session.commit();
            return HttpServletResponse.SC_OK;
        }
    }

    @Override
    public List<Question> getQuestionsForQuiz(Subject subject) {
        List<Question> result = new ArrayList<>();
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            final TreeSet<Integer> integers = getRandomTreeSet(subject.getNumberOfQuestionInQuiz(), subject.getTotalOfValidQuestion());
            final QuestionMapper questionMapper = session.getMapper(QuestionMapper.class);
            final List<Question> questionsForQuiz = questionMapper.getQuestionsForQuiz(subject.getId(), integers.first(), integers.last());


            for (Integer integer : integers) {
                result.add(questionsForQuiz.get(integer - integers.first()));
            }
        }

        return result;
    }

    private TreeSet<Integer> getRandomTreeSet(Integer numberOfQuestionInQuiz, int total) {
        TreeSet<Integer> integers = new TreeSet<>();
        Random random = new Random(System.currentTimeMillis());
        while (integers.size() < numberOfQuestionInQuiz) {
            integers.add(random.nextInt(total));
        }

        return integers;
    }
}