package com.maint.manager.service.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/maintForm")
public class GetMaintFormServlet extends HttpServlet {

    //TODO make post work with form
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        final var writer = response.getWriter();

        writer.println("<html>" + "<body align=\"center\">");
        writer.println("<form action=\"maint\" method=\"post\">"
                + "Maint identifier :  <input type=\"text\" name=\"maintIdentifier\"><br>"
                + "Capability id :     <input type=\"number\" name=\"capabilityId\"><br>"
                + "Created data :      <input type=\"text\" name=\"createdData\"><br>"
                + "Due data :          <input type=\"text\" name=\"dueData\"><br>"
                + "Solve priority id : <input type=\"number\" name=\"solvePriorityId\"><br>"
                + "Estimate :          <input type=\"number\" name=\"estimate\"><br>"
                + "Client :            <input type=\"text\" name=\"client\"><br>"
                + "<input type=\"submit\" value=\"Add maint\""
                + "</form>");
        writer.println("</body>" + "</html>");
    }
}
