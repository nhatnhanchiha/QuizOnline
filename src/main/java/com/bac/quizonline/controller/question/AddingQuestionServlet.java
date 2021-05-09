package com.bac.quizonline.controller.question;

import com.bac.quizonline.model.CreatingModelForServletService;
import com.bac.quizonline.model.entity.Subject;
import com.bac.quizonline.model.impl.CreatingModelForServletServiceImpl;
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
@WebServlet(name = "AddingQuestionServlet", value = "/AddingQuestionServlet")
public class AddingQuestionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");

        SubjectService subjectService = new SubjectServiceImpl();
        List<Subject> subjects = subjectService.getAllCreatedSubjectByIdUser(email);

        request.setAttribute("subjects", subjects);
        RequestDispatcher rd = request.getRequestDispatcher("add-question.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        CreatingModelForServletService creatingModelForServletService = new CreatingModelForServletServiceImpl();
        final Subject subject = creatingModelForServletService.createModelForAddingQuestion(request);
        System.out.println("subject = " + subject);
        if (subject == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        SubjectService subjectService = new SubjectServiceImpl();

        HttpSession session = request.getSession();

        String idUser = (String) session.getAttribute("email");

        subject.setIdUser(idUser);

        final int result = subjectService.addQuestion(subject);
        System.out.println("result = " + result);
        if (result != HttpServletResponse.SC_OK) {
            response.sendError(result);
        } else {
            final RequestDispatcher rd = request.getRequestDispatcher("SearchingQuestionForAdminServlet");
            rd.forward(request, response);
        }
    }
}
