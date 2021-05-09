package com.bac.quizonline.controller.subject;

import com.bac.quizonline.model.entity.Question;
import com.bac.quizonline.model.entity.Subject;
import com.bac.quizonline.model.entity.TakenSubject;
import com.bac.quizonline.model.service.QuestionService;
import com.bac.quizonline.model.service.SubjectService;
import com.bac.quizonline.model.service.TakenSubjectService;
import com.bac.quizonline.model.service.impl.QuestionServiceImpl;
import com.bac.quizonline.model.service.impl.SubjectServiceImpl;
import com.bac.quizonline.model.service.impl.TakenSubjectServiceImpl;

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
@WebServlet(name = "TakingQuizServlet", value = "/TakingQuizServlet")
public class TakingQuizServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idSubjectStr = request.getParameter("idSubject");
        int idSubject;
        try {
            idSubject = Integer.parseInt(idSubjectStr);
        } catch (NumberFormatException ignored) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        final HttpSession session = request.getSession();
        final String email = (String) session.getAttribute("email");

        //Check valid subject
        SubjectService subjectService = new SubjectServiceImpl();
        final Subject validSubject = subjectService.getValidSubject(idSubject);
        if (validSubject == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //Get questions for quiz
        QuestionService questionService = new QuestionServiceImpl();
        final List<Question> questionsForQuiz = questionService.getQuestionsForQuiz(validSubject);
        if (questionsForQuiz == null) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        validSubject.setQuestions(questionsForQuiz);

        //Insert taken subject
        final TakenSubject takenSubject = TakenSubject.TakenSubjectBuilder
                .aTakenSubject()
                .withIdUser(email)
                .withIdSubject(idSubject)
                .withSubject(validSubject)
                .build();
        TakenSubjectService takenSubjectService = new TakenSubjectServiceImpl();
        final int o = takenSubjectService.insert(takenSubject);
        if (o != 1) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        final RequestDispatcher rd = request.getRequestDispatcher("ReloadingQuizServlet");
        rd.forward(request, response);
    }
}
