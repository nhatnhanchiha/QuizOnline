package com.bac.quizonline.controller.user;

import com.bac.quizonline.model.CreatingModelForServletService;
import com.bac.quizonline.model.entity.Role;
import com.bac.quizonline.model.entity.User;
import com.bac.quizonline.model.impl.CreatingModelForServletServiceImpl;
import com.bac.quizonline.model.service.UserService;
import com.bac.quizonline.model.service.exception.DoubleEmailException;
import com.bac.quizonline.model.service.impl.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "RegistrationServlet", value = "/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserServiceImpl();
        final List<Role> roles = userService.getAllRole();
        request.setAttribute("roles", roles);
        final RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CreatingModelForServletService creatingModelForServletService = new CreatingModelForServletServiceImpl();
        final User user = creatingModelForServletService.createModelForRegistration(request);
        UserService userService = new UserServiceImpl();
        try {
            final User registeredUser = userService.register(user);
            if (registeredUser == null) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            } else {
                response.sendRedirect("login");
            }

        } catch (DoubleEmailException e) {
            request.setAttribute("error", e.getMessage());
            doGet(request, response);
        }
    }
}
