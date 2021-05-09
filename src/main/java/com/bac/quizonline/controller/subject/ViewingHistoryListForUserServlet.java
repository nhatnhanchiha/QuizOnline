package com.bac.quizonline.controller.subject;

import com.bac.quizonline.model.component.HistoryListPage;
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
import java.util.List;

/**
 * @author nhatn
 */
@WebServlet(name = "ViewingHistoryListForUserServlet", value = "/ViewingHistoryListForUserServlet")
public class ViewingHistoryListForUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nameOrIdSubjectStr = request.getParameter("Input.NameOrIdSubject");
        Integer nameOrIdSubject = null;

        try {
            nameOrIdSubject = Integer.parseInt(nameOrIdSubjectStr);
            if (nameOrIdSubject < 1) {
                nameOrIdSubject = null;
            }
        } catch (NumberFormatException ignored){
        }

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

        final HttpSession session = request.getSession();
        final String email = (String) session.getAttribute("email");
        TakenSubjectService takenSubjectService = new TakenSubjectServiceImpl();
        final List<TakenSubject> takenSubjectHistoryList = takenSubjectService.getTakenSubjectHistoryList(email, nameOrIdSubjectStr, nameOrIdSubject,HistoryListPage.MAX_SIZE_OF_TAKEN_SUBJECT_LIST * (page - 1), HistoryListPage.MAX_SIZE_OF_TAKEN_SUBJECT_LIST + 1);
        HistoryListPage historyListPage = new HistoryListPage(takenSubjectHistoryList, page);
        request.setAttribute("historyListPage", historyListPage);
        final RequestDispatcher rd = request.getRequestDispatcher("history-list.jsp");
        rd.forward(request, response);
    }
}
