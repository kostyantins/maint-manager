package com.maint.manager.service.servlets;

import com.maint.manager.persistence.dao.MaintDao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static javax.persistence.Persistence.createEntityManagerFactory;

@WebServlet("/maints")
public class GetMaintsServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final var maintDao = new MaintDao(createEntityManagerFactory("MaintManagerDB_H2"));
        final var maintsArray = maintDao.findAll();
        final var out = response.getWriter();

        if (!maintsArray.isEmpty()) {
            out.println(maintsArray);
        } else out.println("No active Maints present !!");
    }
}
