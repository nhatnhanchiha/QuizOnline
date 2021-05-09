package com.bac.quizonline.controller.user;

import com.bac.quizonline.model.entity.User;
import com.bac.quizonline.model.service.UserService;
import com.bac.quizonline.model.service.exception.BlockUserException;
import com.bac.quizonline.model.service.exception.NotFoundUserException;
import com.bac.quizonline.model.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "AuthenticationServlet", value = "/AuthenticationServlet")
public class AuthenticationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("Input.Email");
        String password = request.getParameter("Input.Password");
        UserService userService = new UserServiceImpl();
        try {
            final User user = userService.login(email, password);
            HttpSession session = request.getSession(false);
            session.setAttribute("email", user.getEmail());
            session.setAttribute("role", user.getRoleId());
            session.setAttribute("name", user.getName());
            response.sendRedirect("index");
        } catch (NotFoundUserException | BlockUserException e) {
            request.setAttribute("error", e.getMessage());
            doGet(request, response);
        }
    }
}
