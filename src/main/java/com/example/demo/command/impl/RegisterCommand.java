package com.example.demo.command.impl;

import com.example.demo.command.Command;
import com.example.demo.exception.CommandException;
import com.example.demo.exception.ServiceException;
import com.example.demo.service.UserService;
import com.example.demo.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class RegisterCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String userName = request.getParameter("userName");
        String userEmail = request.getParameter("userEmail");
        String userPass = request.getParameter("userPass");
        String repeatPass = request.getParameter("repeatPass");
        String checkboxValue = request.getParameter("checkbox");
        UserService userService = UserServiceImpl.getInstance();
        String page;
        HttpSession session = request.getSession();
        try {
            if(userService.register(userName, userEmail, userPass, repeatPass, checkboxValue)){
                page = "resources/pages/index.jsp";
            } else {
                request.setAttribute("register_msg", UserServiceImpl.getErrorMessage());
                page = "resources/pages/register.jsp";
            }
            session.setAttribute("current_page", page);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return page;
    }
}
