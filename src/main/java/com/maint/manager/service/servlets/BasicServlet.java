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

        final var out = response.getWriter();

        out.println("<html><body align=\"center\">");
        out.println("<h1>" + "This page is a Maints manager application basic page" + "</h1>");
        out.println("</body></html>");
    }
}
