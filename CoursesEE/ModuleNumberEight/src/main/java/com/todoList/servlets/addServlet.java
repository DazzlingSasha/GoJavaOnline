package com.todoList.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AddServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(request.getParameter("name")+"..add..."+ request.getParameter("category"));

        HttpSession session = request.getSession(true);

        List<Task> allTask;
        int count;
        if (session.getAttribute("task") == null){
            System.out.println("new list");
            allTask = new ArrayList<>();
            count= -1;
        } else {
            System.out.println("load list");
            allTask = (List<Task>) session.getAttribute("task");
            count = allTask.size()-1;
        }

        Task task = new Task();
        task.setName(request.getParameter("name"));
        task.setCategory(request.getParameter("category"));
        task.setId(++count);

        allTask.add(task);

        session.setAttribute("task", allTask);


        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }
}
