package com.bac.quizonline.controller.question;

import com.bac.quizonline.model.CreatingModelForServletService;
import com.bac.quizonline.model.component.ManagePage;
import com.bac.quizonline.model.entity.Question;
import com.bac.quizonline.model.entity.Subject;
import com.bac.quizonline.model.impl.CreatingModelForServletServiceImpl;
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
import java.util.List;

/**
 * @author nhatn
 */
@WebServlet(name = "SearchingQuestionForAdminServlet", value = "/SearchingQuestionForAdminServlet")
public class SearchingQuestionForAdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageStr = request.getParameter("page");
        int page = 1;
        try {
            page = Integer.parseInt(pageStr);
            if (page <= 0) {
                page = 1;
            }
        } catch (NumberFormatException ignored) {
        }

        CreatingModelForServletService creatingModelForServletService = new CreatingModelForServletServiceImpl();
        final Question question = creatingModelForServletService.createModelForAdminSearchQuestion(request);
        final HttpSession session = request.getSession();
        final String email = (String) session.getAttribute("email");
        question.setSubject(Subject.SubjectBuilder.aSubject().withIdUser(email).build());

        QuestionService questionService = new QuestionServiceImpl();
        final List<Question> questions = questionService.getAllQuestions(question, (page - 1) * ManagePage.SIZE, ManagePage.SIZE + 1);

        final Subject subject = question.getSubject();

        subject.setQuestions(questions);

        ManagePage managePage = new ManagePage(subject, page);

        if (question.getIdSubject() != null && !questions.isEmpty()) {
            subject.setDescription(questions.get(0).getSubject().getDescription());
        }
        request.setAttribute("managePage", managePage);
        final RequestDispatcher rd = request.getRequestDispatcher("ManagementQuestionServlet");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
