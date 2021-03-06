package com.maint.manager.service.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.String.format;
import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;

@WebServlet("/commentform")
public class GetCommentFormServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        final var writer = response.getWriter();

        try {
            writer.println("<html>" + "<body align=\"center\">");
            writer.println("<form action=\"comment\" method=\"post\">"
                    + "Comment :           <input type=\"text\" name=\"comment\"><br>"
                    + "Maint id :          <input type=\"number\" name=\"maintId\"><br>"
                    + "<input type=\"submit\" value=\"Add comment\">"
                    + "</form>");
            writer.println("</body>" + "</html>");
        } catch (Exception e) {
            response.setStatus(SC_NOT_FOUND);
            writer.println(format("Comment form was not found due to: '%s'", e.getMessage()));
        }
    }
}
