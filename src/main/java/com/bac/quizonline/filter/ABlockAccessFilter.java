package com.bac.quizonline.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@WebFilter(filterName = "ABlockAccessFilter", value = "/*")
public class ABlockAccessFilter implements Filter {
    private Set<String> blockSet;
    @Override
    public void init(FilterConfig config) throws ServletException {
        blockSet = new HashSet<>();
        blockSet.add("IndexServlet");
        blockSet.add("AuthenticationServlet");
        blockSet.add("RegistrationServlet");

        blockSet.add("ViewingHistoryListForUserServlet");
        blockSet.add("ViewingHistoryDetailServlet");
        blockSet.add("LoggingOutServlet");
        blockSet.add("SearchingSubjectForUserServlet");
        blockSet.add("ReloadingQuizServlet");
        blockSet.add("SavingCurrentPageServlet");
        blockSet.add("SubmitQuizServlet");
        blockSet.add("HistoryTakenSubjectServlet");


        blockSet.add("AddingQuestionServlet");
        blockSet.add("UpdatingQuestionServlet");
        blockSet.add("DeletingQuestionServlet");
        blockSet.add("ManagementQuestionServlet");
        blockSet.add("SearchingQuestionForAdminServlet");

        blockSet.add("add-question.jsp");
        blockSet.add("add-subject.jsp");
        blockSet.add("history-list.jsp");
        blockSet.add("history-quiz.jsp");
        blockSet.add("index.jsp");
        blockSet.add("login.jsp");
        blockSet.add("quiz-manager.jsp");
        blockSet.add("quizzes.jsp");
        blockSet.add("register.jsp");
        blockSet.add("take-quiz.jsp");
        blockSet.add("update-question.jsp");
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        String requestURI = req.getRequestURI();
        String url = requestURI.substring(requestURI.lastIndexOf('/') + 1);
        if (!blockSet.contains(url)) {
            chain.doFilter(request, response);
        } else {
            HttpServletResponse res = (HttpServletResponse) response;
            res.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
