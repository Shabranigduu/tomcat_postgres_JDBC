package com.example.tomcat_maven_postgresql;

import dao.TicketDao;
import entity.TicketEntity;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "TicketServlet", value = "/TicketServlet")
public class TicketServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

              Long id = Long.parseLong(request.getParameter("id"));

        TicketEntity ticket = TicketDao.getInstance().getById(id);

        PrintWriter pw = response.getWriter();
        pw.println("<html>");
        pw.println("<h1> Билет № " +id + "</h>");
        pw.println("<h1>" +ticket+ "</h>");
        pw.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


}

