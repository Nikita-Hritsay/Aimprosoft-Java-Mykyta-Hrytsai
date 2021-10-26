package com.aimprosoft.aimlearning.controllers;

import com.aimprosoft.aimlearning.commands.CommandFactory;
import com.aimprosoft.aimlearning.commands.ICommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainController extends HttpServlet {

    private final CommandFactory commandFactory = new CommandFactory();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final ICommand frontController = commandFactory.getCommand(request.getRequestURI());
        request.setCharacterEncoding("UTF-8");
        try {
            frontController.service(request, response);
        } catch (Exception e) {
            request.setAttribute("error", e.toString());
            request.getRequestDispatcher("WEB-INF/pages/errorPage.jsp").forward(request, response);
        }
    }

}
