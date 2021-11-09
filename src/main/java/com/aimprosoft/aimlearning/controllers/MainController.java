package com.aimprosoft.aimlearning.controllers;

import com.aimprosoft.aimlearning.commands.CommandFactory;
import com.aimprosoft.aimlearning.commands.ICommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class MainController extends HttpServlet {

    private static CommandFactory commandFactory;

    @Autowired
    public void setCommandFactory(CommandFactory commandFactory) {
        MainController.commandFactory = commandFactory;
    }

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
