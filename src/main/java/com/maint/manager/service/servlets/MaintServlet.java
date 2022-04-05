package com.maint.manager.service.servlets;

import com.maint.manager.persistence.dao.MaintDao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static javax.persistence.Persistence.createEntityManagerFactory;

@WebServlet("/maints")
public class MaintServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final var maintsArray = new MaintDao(createEntityManagerFactory("MaintManagerDB_H2")).findAll();

        final var out = response.getWriter();
        out.println(maintsArray.get(0).getClient());
    }
}
