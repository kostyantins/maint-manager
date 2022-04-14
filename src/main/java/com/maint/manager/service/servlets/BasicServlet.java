package com.maint.manager.service.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class BasicServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        final var writer = response.getWriter();

        writer.println("<html><body align=\"center\">");
        writer.println("<h1>" + "This page is a Maints manager application basic page" + "</h1>");
        writer.println("<h2>" + "Call: GET '/maints' to gather all existing maints" + "</h2>");
        writer.println("<h2>" + "Call: POST '/maint' to create a new maint" + "</h2>");
        writer.println("</body></html>");
    }
}
