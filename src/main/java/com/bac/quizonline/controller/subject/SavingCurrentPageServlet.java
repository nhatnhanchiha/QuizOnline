package com.bac.quizonline.controller.subject;

import com.bac.quizonline.model.entity.AnswerDetail;
import com.bac.quizonline.model.entity.TakenSubject;
import com.bac.quizonline.model.service.AnswerDetailService;
import com.bac.quizonline.model.service.TakenSubjectService;
import com.bac.quizonline.model.service.impl.AnswerDetailServiceImpl;
import com.bac.quizonline.model.service.impl.TakenSubjectServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@WebServlet(name = "SavingCurrentPageServlet", value = "/SavingCurrentPageServlet")
public class SavingCurrentPageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idTakenSubjectStr = request.getParameter("idTakenSubject");
        int idTakenSubject;
        try {
            idTakenSubject = Integer.parseInt(idTakenSubjectStr);
            if (idTakenSubject < 1) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }
        } catch (NumberFormatException ignored) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        String idSubjectStr = request.getParameter("idSubject");
        int idSubject;
        try {
            idSubject = Integer.parseInt(idSubjectStr);
            if (idSubject < 0) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }
        } catch (NumberFormatException ignored) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        //check quiz in progress
        final HttpSession session = request.getSession();
        final String email = (String) session.getAttribute("email");
        TakenSubjectService takenSubjectService = new TakenSubjectServiceImpl();
        final TakenSubject takenSubjectInQuiz = takenSubjectService.getTakenSubjectInQuiz(TakenSubject.TakenSubjectBuilder
                .aTakenSubject()
                .withIdSubject(idSubject)
                .withIdUser(email).build());
        if (takenSubjectInQuiz == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        List<String> parameterNames = Collections.list(request.getParameterNames());
        List<AnswerDetail> answerDetails = new ArrayList<>();
        for (String parameterName : parameterNames) {
            if (parameterName.startsWith("as-")) {
                int questionId;
                int optionId;
                try {
                    questionId = Integer.parseInt(parameterName.substring(parameterName.lastIndexOf("as-") + 3));
                    final String[] parameterValues = request.getParameterValues(parameterName);
                    for (String parameterValue : parameterValues) {
                        optionId = Integer.parseInt(parameterValue);
                        answerDetails.add(AnswerDetail.AnswerDetailBuilder.anAnswerDetail()
                                .withIdTakenSubject(idTakenSubject)
                                .withIdQuestion(questionId)
                                .withIdOption(optionId).build());
                    }
                } catch (NumberFormatException ignored) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                    return;
                }
            }
        }

        AnswerDetailService answerDetailService = new AnswerDetailServiceImpl();
        answerDetailService.insertList(answerDetails);

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
        response.sendRedirect("take-quiz?idSubject="+ idSubject +"&page=" + page);
    }
}
