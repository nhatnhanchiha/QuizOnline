package com.bac.quizonline.model;

import com.bac.quizonline.model.entity.Question;
import com.bac.quizonline.model.entity.Subject;
import com.bac.quizonline.model.entity.User;

import javax.servlet.http.HttpServletRequest;

public interface CreatingModelForServletService {
    int MIN_LENGTH_OF_EMAIL = 2;
    int MAX_LENGTH_OF_EMAIL = 256;
    String EMAIL_REGEX = "^\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b$";

    int MIN_LENGTH_OF_NAME = 2;
    int MAX_LENGTH_OF_NAME = 250;

    int MIN_LENGTH_OF_PASSWORD = 6;
    int MAX_LENGTH_OF_PASSWORD = 256;


    int MAX_LENGTH_OF_TEXT_QUESTION = 256;
    int MIN_LENGTH_OF_TEXT_QUESTION = 1;

    int MAX_LENGTH_OF_CONTENT_QUESTION = 1024;
    int MIN_LENGTH_OF_CONTENT_QUESTION = 1;

    int MAX_LENGTH_OF_CONTENT_OPTION = 1024;
    int MIN_LENGTH_OF_CONTENT_OPTION = 1;

    int MAX_LENGTH_OF_NAME_OF_SUBJECT = 256;
    int MIN_LENGTH_OF_NAME_OF_SUBJECT = 2;

    int MAX_LENGTH_OF_DESCRIPTION_OF_SUBJECT = 450;
    int MIN_LENGTH_OF_DESCRIPTION_OF_SUBJECT = 2;

    double MAX_VALUE_OF_MINUTES = 1440;
    double MIN_VALUE_OF_MINUTES = 1;

    int MAX_VALUE_OF_NUMBER_QUESTION_IN_QUIZ = 1440;
    int MIN_VALUE_OF_NUMBER_QUESTION_IN_QUIZ = 1;

    Question createModelForUpdatingQuestion(HttpServletRequest request);

    User createModelForRegistration(HttpServletRequest request);

    Question createModelForAdminSearchQuestion(HttpServletRequest request);

    Subject createModelForAddingQuestion(HttpServletRequest request);



}
