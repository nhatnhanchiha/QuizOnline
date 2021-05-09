package com.bac.quizonline.model.service;

import com.bac.quizonline.model.entity.Question;
import com.bac.quizonline.model.entity.Subject;

import java.util.List;

public interface QuestionService {
    List<Question> getAllQuestions(Question question, int offset, int limit);

    int deleteQuestion(Question question);

    int updateQuestion(Question question);

    List<Question> getQuestionsForQuiz(Subject subject);
}
