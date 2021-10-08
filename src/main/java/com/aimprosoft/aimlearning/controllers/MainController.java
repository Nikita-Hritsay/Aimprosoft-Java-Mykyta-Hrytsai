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
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final ICommand frontController = commandFactory.getCommand(req.getRequestURI());
        try{
            frontController.service(req, resp);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}
