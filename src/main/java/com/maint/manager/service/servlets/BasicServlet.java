package com.maint.manager.service.servlets;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static javax.persistence.Persistence.createEntityManagerFactory;

@WebServlet("/")
public class BasicServlet extends HttpServlet {

    //TODO not sure the variable is thread safe
    public static EntityManagerFactory entityManagerFactory;

    @Override
    public void init() throws ServletException {
        super.init();
        entityManagerFactory = createEntityManagerFactory("MaintManagerDB_H2");
    }

    @Override
    public void destroy() {
        super.destroy();
        entityManagerFactory.close();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        final var writer = response.getWriter();

        writer.println("<html><body align=\"center\">");
        writer.println("<h1>" + "This page is a Maints manager application basic page" + "</h1>");
        writer.println("<h2>" + "Call: GET '/maints' to gather all existing maints" + "</h2>");
        writer.println("<h2>" + "Call: GET '/maintsbyid' to get appropriate maint by id" + "</h2>");
        writer.println("<h2>" + "Call: GET '/maintform' to get web form to add a maint" + "</h2>");
        writer.println("<h2>" + "Call: GET '/commentform' to get web form to add a comment to appropreate maint" + "</h2>");
        writer.println("<h2>" + "Call: GET '/comments' to get all existing comments to appropreate maint" + "</h2>");
        writer.println("<h2>" + "Call: GET '/commentbyid' to get appropriate comment by id" + "</h2>");
        writer.println("<h2>" + "Call: POST '/maint' to create a new maint" + "</h2>");
        writer.println("<h2>" + "Call: POST '/comment' to create a new maint comment" + "</h2>");
        writer.println("</body></html>");
    }
}
