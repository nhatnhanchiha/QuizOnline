package com.bac.quizonline.model.impl;

import com.bac.quizonline.model.CreatingModelForServletService;
import com.bac.quizonline.model.entity.Option;
import com.bac.quizonline.model.entity.Question;
import com.bac.quizonline.model.entity.Subject;
import com.bac.quizonline.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author nhatn
 */
public final class CreatingModelForServletServiceImpl implements CreatingModelForServletService {
    @Override
    public Question createModelForUpdatingQuestion(HttpServletRequest request) {
        String questionIdStr = request.getParameter("QuestionId");
        int questionId;
        try {
            questionId = Integer.parseInt(questionIdStr);
            if (questionId <= 0) {
                return null;
            }
        } catch (NumberFormatException ignored) {
            return null;
        }
        String idSubjectStr = request.getParameter("Select.Subject");
        int idSubject;
        try {
            idSubject = Integer.parseInt(idSubjectStr);
            if (idSubject <= 0) {
                return null;
            }
        } catch (NumberFormatException ignored) {
            return null;
        }

        String questionText = request.getParameter("Input.QuestionText");
        if (questionText == null
                || questionText.trim().length() < MIN_LENGTH_OF_TEXT_QUESTION
                || questionText.length() > MAX_LENGTH_OF_TEXT_QUESTION) {
            return null;
        }


        String questionContent = request.getParameter("Input.QuestionContent");
        if (questionContent == null
                || questionContent.trim().length() < MIN_LENGTH_OF_CONTENT_QUESTION
                || questionContent.length() > MAX_LENGTH_OF_CONTENT_QUESTION) {
            return null;
        }


        List<Option> options = new ArrayList<>();

        final ArrayList<String> parameterNames = Collections.list(request.getParameterNames());
        for (String name : parameterNames) {
            if (name.startsWith("Input.Question1Option")) {
                try {
                    int optionIndex = Integer.parseInt(name.substring(name.indexOf("Input.Question1Option") + 21));
                    String optionText = request.getParameter(name);
                    if (optionText == null
                            || optionText.trim().length() < MIN_LENGTH_OF_TEXT_QUESTION
                            || optionText.length() > MAX_LENGTH_OF_TEXT_QUESTION) {
                        return null;
                    }

                    String optionIdStr = request.getParameter("Option" + optionIndex + "Id");
                    Integer optionId = null;
                    if (optionIdStr != null) {
                        optionId = Integer.parseInt(optionIdStr);
                    }

                    Boolean isRightAnswer = request.getParameter("CheckBox.Question1RightOption" + optionIndex) != null;

                    final Option option = Option.OptionBuilder.anOption()
                            .withId(optionId)
                            .withContent(optionText)
                            .withIsRightAnswer(isRightAnswer)
                            .withIdQuestion(questionId)
                            .build();

                    options.add(option);
                } catch (NumberFormatException ignored) {
                    return null;
                }
            }
        }

        return Question.QuestionBuilder
                .aQuestion()
                .withId(questionId)
                .withText(questionText)
                .withIdSubject(idSubject)
                .withContent(questionContent)
                .withStatus(true)
                .withOptions(options)
                .build();
    }

    @Override
    public User createModelForRegistration(HttpServletRequest request) {
        final String email = request.getParameter("Input.Email");
        if (email == null
                || email.trim().length() < MIN_LENGTH_OF_EMAIL
                || email.length() > MAX_LENGTH_OF_EMAIL
                || !email.matches(EMAIL_REGEX)) {
            return null;
        }

        final String name = request.getParameter("Input.Name");
        if (name == null
                || name.trim().length() < MIN_LENGTH_OF_NAME
                || name.length() > MAX_LENGTH_OF_NAME) {
            return null;
        }

        final String password = request.getParameter("Input.Password");
        if (password == null || password.length() < MIN_LENGTH_OF_PASSWORD || password.length() > MAX_LENGTH_OF_PASSWORD) {
            return null;
        }

        final String roleStr = request.getParameter("Select.Role");
        short role;
        try {
            role = Short.parseShort(roleStr);
        } catch (NumberFormatException ignored) {
            return null;
        }

        return User.UserBuilder
                .anUser()
                .withEmail(email)
                .withName(name)
                .withPassword(password)
                .withRoleId(role)
                .withStatus(true)
                .build();
    }

    @Override
    public Question createModelForAdminSearchQuestion(HttpServletRequest request) {
        String contentOfQuestion = request.getParameter("Input.ContentOfQuestion");
        String idSubjectStr = request.getParameter("Select.Subject");
        Integer idSubject = null;
        try {
            idSubject = Integer.parseInt(idSubjectStr);
            if (idSubject < 1) {
                idSubject = null;
            }
        } catch (NumberFormatException ignored) {
        }

        String statusStr = request.getParameter("Select.Status");
        Boolean status = null;
        if ("0".equals(statusStr) || "1".equals(statusStr)) {
            status = "1".equals(statusStr);
        }

        return Question.QuestionBuilder
                .aQuestion()
                .withContent(contentOfQuestion)
                .withIdSubject(idSubject)
                .withStatus(status)
                .build();
    }

    @Override
    public Subject createModelForAddingQuestion(HttpServletRequest request) {
        List<String> parameterNames = Collections.list(request.getParameterNames());
        String subjectIdStr = request.getParameter("Select.Subject");
        int idSubject;
        try {
            idSubject = Integer.parseInt(subjectIdStr);
            if (idSubject <= 0) {
                return null;
            }
        } catch (NumberFormatException ignore) {
            return null;
        }


        List<Integer> questionIds = new ArrayList<>();
        for (String name : parameterNames) {
            if (name.startsWith("Input.Question") && name.endsWith("Text")) {
                questionIds.add(Integer.parseInt(name.substring(name.indexOf("Input.Question") + "Input.Question".length(), name.lastIndexOf("Text"))));
            }
        }

        List<Question> questions = new ArrayList<>();
        for (Integer questionId : questionIds) {
            String questionIdStr = "Question" + questionId;
            String text = request.getParameter("Input." + questionIdStr + "Text");
            if (text == null) {
                return null;
            }

            String content = request.getParameter("Input." + questionIdStr + "Content");
            if (content == null) {
                return null;
            }

            List<Option> options = new ArrayList<>();
            for (String name : parameterNames) {
                if (name.startsWith("Input." + questionIdStr + "Option")) {
                    String optionText = request.getParameter(name);
                    if (optionText == null) {
                        return null;
                    }
                    options.add(Option.OptionBuilder
                            .anOption().withContent(optionText)
                            .withIsRightAnswer(request.getParameter("CheckBox." + questionIdStr + "RightOption" + name.substring(name.lastIndexOf("Option") + "Option".length())) != null)
                            .build());
                }
            }

            questions.add(Question.QuestionBuilder
                    .aQuestion()
                    .withContent(content)
                    .withText(text)
                    .withOptions(options)
                    .withCreatedDate(LocalDate.now())
                    .withStatus(true)
                    .build());
        }


        return Subject.SubjectBuilder.aSubject()
                .withId(idSubject)
                .withQuestions(questions)
                .build();
    }

}
