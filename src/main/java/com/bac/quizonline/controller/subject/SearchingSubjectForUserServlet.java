package com.bac.quizonline.controller.subject;

import com.bac.quizonline.model.component.QuizzesPage;
import com.bac.quizonline.model.entity.Subject;
import com.bac.quizonline.model.service.SubjectService;
import com.bac.quizonline.model.service.impl.SubjectServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SearchingSubjectForUserServlet", value = "/SearchingSubjectForUserServlet")
public class SearchingSubjectForUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageStr = request.getParameter("page");
        int page;
        String nameOrIdSubject = request.getParameter("Input.NameOrIdSubject");
        SubjectService subjectService = new SubjectServiceImpl();
        Integer idSubject = null;
        try {
            idSubject = Integer.parseInt(nameOrIdSubject);
            page = Integer.parseInt(pageStr);
            if (page < 1) {
                page = 1;
            }
        } catch (NumberFormatException ignored) {
            page = 1;
        }

        final Subject subject = Subject.SubjectBuilder.aSubject()
                .withId(idSubject)
                .withName(nameOrIdSubject)
                .build();

        final List<Subject> subjects = subjectService.getAllSubject(subject, (page - 1) * QuizzesPage.MAX_SIZE_OF_SUBJECT_LIST, QuizzesPage.MAX_SIZE_OF_SUBJECT_LIST + 1);
        QuizzesPage quizzesPage = new QuizzesPage(subjects, page);

        request.setAttribute("quizzesPage", quizzesPage);
        final RequestDispatcher rd = request.getRequestDispatcher("quizzes.jsp");
        rd.forward(request, response);
    }
}
