package com.maint.manager.service.servlets;

import com.maint.manager.persistence.dao.MaintDao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.maint.manager.service.servlets.BasicServlet.entityManagerFactory;
import static java.lang.Long.parseLong;
import static java.lang.String.format;
import static javax.servlet.http.HttpServletResponse.SC_NO_CONTENT;
import static javax.servlet.http.HttpServletResponse.SC_OK;

@WebServlet("/maintbyid")
public class GetMaintByIdServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final var maintDao = new MaintDao(entityManagerFactory);
        final var maintId = request.getParameter("id");
        final var maint = maintDao.findById(parseLong(maintId));
        final var writer = response.getWriter();

        if (maint.isPresent()) {
            response.setStatus(SC_OK);
            writer.println(maint);
        } else {
            response.setStatus(SC_NO_CONTENT);
            writer.println(format("No active Maint with id: %s present !!", maintId));
        }
    }
}
