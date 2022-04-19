package com.maint.manager.service.servlets;

import com.maint.manager.persistence.dao.MaintDao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.maint.manager.service.servlets.BasicServlet.entityManagerFactory;

@WebServlet("/maints")
public class GetMaintsServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final var maintDao = new MaintDao(entityManagerFactory);
        final var maintsArray = maintDao.findAll();
        final var writer = response.getWriter();

        if (!maintsArray.isEmpty()) {
            writer.println(maintsArray);
        } else writer.println("No active Maints present !!");
    }
}
