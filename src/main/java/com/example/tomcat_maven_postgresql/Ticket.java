package com.example.tomcat_maven_postgresql;

import dao.TicketDao;
import entity.TicketEntity;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "Ticket", value = "/Ticket")
public class Ticket extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        List<TicketEntity> ticketList = TicketDao.getInstance().getAll();

        PrintWriter pw = response.getWriter();

        pw.println("""
                <html>
                <head>
                <style>
                table, th, td {
                  border: 1px solid black;
                }
                </style>
                </head>
                <body>
                                
                <table>
                  <tr>
                    <th>id</th>
                    <th>passenger_no</th>
                    <th>passenger_name</th>
                    <th>flight_id</th>
                    <th>seat_no</th>
                    <th>cost</th>
                  </tr>""");

        for (TicketEntity ticket : ticketList) {
            pw.println("<tr>"+
                    "<th>"+ ticket.getId() + "</th>"+
                    "<th>"+ ticket.getPassengerNo() + "</th>"+
                    "<th>"+ ticket.getPassengerName() + "</th>"+
                    "<th>"+ ticket.getFlightId() + "</th>"+
                    "<th>"+ ticket.getSeatNo() + "</th>"+
                    "<th>"+ ticket.getCost() + "</th>"+
                    "</tr>");
        }
        pw.println("""
                    </table>                                    
                    </body>
                    </html>
                    """);
    }

        @Override
        protected void doPost (HttpServletRequest request, HttpServletResponse response) throws
        ServletException, IOException {

        }
}

