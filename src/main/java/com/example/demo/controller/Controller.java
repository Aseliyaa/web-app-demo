package com.example.demo.controller;

import java.io.*;

import com.example.demo.command.Command;
import com.example.demo.command.CommandType;
import com.example.demo.exception.CommandException;
import com.example.demo.pool.ConnectionPool;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@WebServlet(name = "helloServlet", urlPatterns = {"/controller", "*.do"})
public class Controller extends HttpServlet {
    static Logger logger = LogManager.getLogger();

    public void init() {
        ConnectionPool.getInstance();
        logger.log(Level.INFO, "---------> Servlet Init:" + this.getServletInfo());
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        String commandStr = request.getParameter("command");
        Command command = CommandType.define(commandStr);
        String page;
        try {
            page = command.execute(request);
            request.getRequestDispatcher(page).forward(request, response);
        } catch (CommandException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    public void destroy() {
        ConnectionPool.getInstance().destroyPool();
        logger.log(Level.INFO, "---------> Servlet Destroyed:" + this.getServletName());
    }
}