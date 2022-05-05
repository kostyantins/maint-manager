package com.maint.manager.service.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.String.format;
import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;

//TODO use .jsp file instead of such servlet, it is inappropriate to user html in such way
@WebServlet("/maintform")
public class GetMaintFormServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        final var writer = response.getWriter();

        //TODO read .jsp somehove
        //var maintRorm = request.getRequestDispatcher("GetMaintForm.jsp");

        try {
            writer.println("<html>" + "<body align=\"center\">");
            writer.println("<form action=\"maint\" method=\"post\">"
                    + "Maint identifier :  <input type=\"text\" name=\"maintIdentifier\"><br>"
                    + "Capability id :     <input type=\"number\" name=\"capabilityId\"><br>"
                    + "Due data :          <input type=\"text\" name=\"dueData\"><br>"
                    + "Solve priority id : <input type=\"number\" name=\"solvePriorityId\"><br>"
                    + "Estimate :          <input type=\"number\" name=\"estimate\"><br>"
                    + "Client :            <input type=\"text\" name=\"client\"><br>"
                    + "Comment :           <input type=\"text\" name=\"comment\"><br>"
                    + "Fix version :       <input type=\"text\" name=\"fixVersion\"><br>"
                    + "<input type=\"submit\" value=\"Add maint\">"
                    + "</form>");
            writer.println("</body>" + "</html>");
        } catch (Exception e) {
            response.setStatus(SC_NOT_FOUND);
            writer.println(format("Maint form was not found due to: '%s'", e.getMessage()));
        }
    }
}
