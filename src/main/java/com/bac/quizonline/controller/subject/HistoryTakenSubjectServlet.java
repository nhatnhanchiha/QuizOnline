package com.bac.quizonline.controller.subject;

import com.bac.quizonline.model.component.HistoryPage;
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

@WebServlet(name = "HistoryTakenSubjectServlet", value = "/HistoryTakenSubjectServlet")
public class HistoryTakenSubjectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idTakenSubjectStr = request.getParameter("idTakenSubject");
        int idTakenSubject;
        try {
            idTakenSubject = Integer.parseInt(idTakenSubjectStr);
        } catch (NumberFormatException ignored) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        final HttpSession session = request.getSession();
        final String email = (String) session.getAttribute("email");
        TakenSubjectService takenSubjectService = new TakenSubjectServiceImpl();
        final TakenSubject takenSubject = takenSubjectService.checkValidTakenSubjectId(idTakenSubject, email);
        if (takenSubject == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        final TakenSubject takenSubjectWithDetail = takenSubjectService.getTakenSubjectWithDetail(takenSubject);
        HistoryPage historyPage = new HistoryPage(takenSubjectWithDetail);

        request.setAttribute("historyPage", historyPage);
        final RequestDispatcher rd = request.getRequestDispatcher("history-quiz.jsp");
        rd.forward(request, response);
    }
}
