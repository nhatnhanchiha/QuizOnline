package com.bac.quizonline.controller.question;

import com.bac.quizonline.model.entity.Question;
import com.bac.quizonline.model.entity.Subject;
import com.bac.quizonline.model.service.QuestionService;
import com.bac.quizonline.model.service.impl.QuestionServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author nhatn
 */
@WebServlet(name = "DeletingQuestionServlet", value = "/DeletingQuestionServlet")
public class DeletingQuestionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final HttpSession session = request.getSession();
        final String email = (String) session.getAttribute("email");
        String questionIdStr = request.getParameter("id");
        int questionId;
        try {
            questionId = Integer.parseInt(questionIdStr);
        } catch (NumberFormatException ignored) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        final Subject subject = Subject.SubjectBuilder
                .aSubject()
                .withIdUser(email)
                .build();

        final Question question = Question.QuestionBuilder
                .aQuestion()
                .withSubject(subject)
                .withId(questionId)
                .build();

        QuestionService questionService = new QuestionServiceImpl();
        final int result = questionService.deleteQuestion(question);
        if (result != HttpServletResponse.SC_OK) {
            response.sendError(result);
        } else {
            final RequestDispatcher rd = request.getRequestDispatcher("SearchingQuestionForAdminServlet");
            rd.forward(request, response);
        }
    }
}
