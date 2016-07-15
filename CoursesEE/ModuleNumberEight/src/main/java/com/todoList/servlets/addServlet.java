package com.todoList.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AddServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Task.addTask(request.getParameter("name"), request.getParameter("category"));
        System.out.println(request.getParameter("name")+"....."+ request.getParameter("category"));
        response.addHeader(request.getParameter("name"), request.getParameter("category"));
    }
}
