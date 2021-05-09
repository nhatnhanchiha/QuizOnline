package com.bac.quizonline.controller.question;

import com.bac.quizonline.model.entity.Subject;
import com.bac.quizonline.model.service.SubjectService;
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

/**
 * @author nhatn
 */
@WebServlet(name = "ManagementQuestionServlet", value = "/ManagementQuestionServlet")
public class ManagementQuestionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final HttpSession session = request.getSession(false);
        final String email = (String) session.getAttribute("email");
        SubjectService subjectService = new SubjectServiceImpl();
        final List<Subject> subjects = subjectService.getAllCreatedSubjectByIdUser(email);
        request.setAttribute("subjects", subjects);
        final RequestDispatcher rd = request.getRequestDispatcher("quiz-manager.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
