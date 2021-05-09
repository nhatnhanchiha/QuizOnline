package com.bac.quizonline.controller.subject;

import com.bac.quizonline.model.component.QuizPage;
import com.bac.quizonline.model.entity.TakenSubject;
import com.bac.quizonline.model.service.TakenSubjectService;
import com.bac.quizonline.model.service.impl.TakenSubjectServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ReloadingQuizServlet", value = "/ReloadingQuizServlet")
public class ReloadingQuizServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageStr = request.getParameter("page");
        int page;
        try {
            page = Integer.parseInt(pageStr);
            if (page < 1) {
                page = 1;
            }
        } catch (NumberFormatException ignored) {
            page = 1;
        }

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
        final TakenSubject takenSubject = TakenSubject
                .TakenSubjectBuilder
                .aTakenSubject()
                .withIdUser(email)
                .withIdSubject(idSubject)
                .build();
        TakenSubjectService takenSubjectService = new TakenSubjectServiceImpl();
        final TakenSubject takenSubjectInQuiz = takenSubjectService.getTakenSubjectInQuiz(takenSubject);
        if (takenSubjectInQuiz == null) {
            final RequestDispatcher rd = request.getRequestDispatcher("TakingQuizServlet");
            rd.forward(request, response);
            return;
        }

        final TakenSubject takenSubjectWithDetail = takenSubjectService.getTakenSubjectWithDetailInProgressQuiz(takenSubjectInQuiz, (page - 1) * QuizPage.MAX_QUESTION_OF_A_PAGE, QuizPage.MAX_QUESTION_OF_A_PAGE + 1);
        if (takenSubjectWithDetail == null) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } else {
            QuizPage quizPage = new QuizPage(takenSubjectWithDetail, page);
            request.setAttribute("quizPage", quizPage);
            final RequestDispatcher rd = request.getRequestDispatcher("take-quiz.jsp");
            rd.forward(request, response);
        }
    }
}
