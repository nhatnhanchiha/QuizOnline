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
@WebFilter(filterName = "AdminAccessingFilter", value = "/*")
public class AdminAccessingFilter implements Filter {
    private Set<String> actionsSet;
    @Override
    public void init(FilterConfig config) throws ServletException {
        actionsSet = new HashSet<>();
        actionsSet.add("add-question");
        actionsSet.add("edit-question");
        actionsSet.add("delete-question");
        actionsSet.add("manage-quiz");
        actionsSet.add("search-question");
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
        if (actionsSet.contains(url) && (role == null || 2 != role)) {
            HttpServletResponse resp = (HttpServletResponse) response;
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        } else {
            chain.doFilter(request, response);
        }
    }
}
