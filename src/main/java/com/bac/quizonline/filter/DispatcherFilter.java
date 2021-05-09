package com.bac.quizonline.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author nhatn
 */
@WebFilter(filterName = "DispatcherFilter", urlPatterns = "/*")
public class DispatcherFilter implements Filter {
    private Map<String, String> urlMap;

    @Override
    public void init(FilterConfig config) throws ServletException {
        urlMap = new HashMap<>(64);

        //guest
        urlMap.put("", "IndexServlet");
        urlMap.put("index", "IndexServlet");
        urlMap.put("login", "AuthenticationServlet");
        urlMap.put("register", "RegistrationServlet");

        //student
        urlMap.put("view-history-list", "ViewingHistoryListForUserServlet");
        urlMap.put("view-history-detail", "ViewingHistoryDetailServlet");
        urlMap.put("log-out", "LoggingOutServlet");
        urlMap.put("view-quiz-list", "SearchingSubjectForUserServlet");
        //hard vkl
        urlMap.put("take-quiz", "ReloadingQuizServlet");
        urlMap.put("save_current_page", "SavingCurrentPageServlet");
        urlMap.put("submit_quiz", "SubmitQuizServlet");
        urlMap.put("history_quiz", "HistoryTakenSubjectServlet");

        //admin
        urlMap.put("add-question", "AddingQuestionServlet");
        urlMap.put("edit-question", "UpdatingQuestionServlet");
        urlMap.put("delete-question", "DeletingQuestionServlet");
        urlMap.put("manage-quiz", "ManagementQuestionServlet");
        urlMap.put("search-question", "SearchingQuestionForAdminServlet");
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        String requestURI = req.getRequestURI();
        String url = requestURI.substring(requestURI.lastIndexOf('/') + 1);
        url = urlMap.get(url);
        if (url != null) {
            RequestDispatcher rd = req.getRequestDispatcher(url);
            rd.forward(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }
}
