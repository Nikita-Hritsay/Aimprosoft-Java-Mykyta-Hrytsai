package com.aimprosoft.aimlearning.commands;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ICommand {

    void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;

}