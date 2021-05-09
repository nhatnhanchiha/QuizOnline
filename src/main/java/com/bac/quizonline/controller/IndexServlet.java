package com.bac.quizonline.controller;

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

@WebServlet(name = "IndexServlet", value = "/IndexServlet")
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SubjectService subjectService = new SubjectServiceImpl();
        final List<Subject> subjects = subjectService.getAllSubject(null, 0, 5);
        request.setAttribute("subjects", subjects);
        final RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
