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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author nhatn
 */
@WebFilter(filterName = "BasicUserAccessingFilter", value = "/*")
public class BasicUserAccessingFilter implements Filter {
    private Set<String> actionSet;
    @Override
    public void init(FilterConfig config) throws ServletException {
        actionSet = new HashSet<>();
        actionSet.add("view-history-list");
        actionSet.add("view-history-detail");
        actionSet.add("take-quiz");
        actionSet.add("save_current_page");
        actionSet.add("submit_quiz");
        actionSet.add("history_quiz");
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        final HttpSession session = req.getSession();
        final Short role = (Short) session.getAttribute("role");
        String requestURI = req.getRequestURI();
        String url = requestURI.substring(requestURI.lastIndexOf('/') + 1);
        if (actionSet.contains(url) && (role == null || 1 != role)) {
            HttpServletResponse resp = (HttpServletResponse) response;
            if (role != null && role == 2) {
                resp.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
                return;
            }
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            chain.doFilter(request, response);
        }
    }
}
