package com.bac.quizonline.controller.question;

import com.bac.quizonline.model.CreatingModelForServletService;
import com.bac.quizonline.model.entity.Question;
import com.bac.quizonline.model.entity.Subject;
import com.bac.quizonline.model.impl.CreatingModelForServletServiceImpl;
import com.bac.quizonline.model.service.QuestionService;
import com.bac.quizonline.model.service.SubjectService;
import com.bac.quizonline.model.service.impl.QuestionServiceImpl;
import com.bac.quizonline.model.service.impl.SubjectServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UpdatingQuestionServlet", value = "/UpdatingQuestionServlet")
public class UpdatingQuestionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String questionIdStr = request.getParameter("id");
        int questionId;
        try {
            questionId = Integer.parseInt(questionIdStr);
        } catch (NumberFormatException ignored) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        HttpSession session = request.getSession();
        final String email = (String) session.getAttribute("email");
        final Subject subject = Subject.SubjectBuilder
                .aSubject()
                .withIdUser(email)
                .build();

        final Question question = Question.QuestionBuilder.aQuestion()
                .withId(questionId)
                .withSubject(subject)
                .build();

        QuestionService questionService = new QuestionServiceImpl();
        final List<Question> questions = questionService.getAllQuestions(question, 0, 1);

        if (questions.isEmpty()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        } else {
            SubjectService subjectService = new SubjectServiceImpl();
            List<Subject> subjects = subjectService.getAllCreatedSubjectByIdUser(email);
            request.setAttribute("subjects", subjects);
            request.setAttribute("question", questions.get(0));
            final RequestDispatcher rd = request.getRequestDispatcher("update-question.jsp");
            rd.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        CreatingModelForServletService creatingModelForServletService = new CreatingModelForServletServiceImpl();
        final Question question = creatingModelForServletService.createModelForUpdatingQuestion(request);
        if (question == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        final HttpSession session = request.getSession();
        final String email = (String) session.getAttribute("email");
        final Subject subject = Subject.SubjectBuilder
                .aSubject()
                .withIdUser(email)
                .withId(question.getIdSubject())
                .build();
        question.setSubject(subject);
        QuestionService questionService = new QuestionServiceImpl();
        final int code = questionService.updateQuestion(question);
        if (code != HttpServletResponse.SC_OK) {
            response.sendError(code);
        } else {
            final RequestDispatcher rd = request.getRequestDispatcher("SearchingQuestionForAdminServlet");
            rd.forward(request, response);
        }
    }
}
