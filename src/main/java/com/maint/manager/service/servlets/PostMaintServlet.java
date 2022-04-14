package com.maint.manager.service.servlets;

import com.maint.manager.persistence.dao.MaintDao;
import com.maint.manager.persistence.entities.Maint;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

import static java.time.format.DateTimeFormatter.ofPattern;
import static javax.persistence.Persistence.createEntityManagerFactory;

@WebServlet("/maint")
public class PostMaintServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //TODO remove somehow duplication of DB init
        final var maintDao = new MaintDao(createEntityManagerFactory("MaintManagerDB_H2"));
        final var maint = Maint.builder()
                .maintIdentifier(request.getParameter("maintIdentifier"))
                .capabilityId(Long.valueOf(request.getParameter("capabilityId")))
                .createdData(LocalDate.parse(request.getParameter("createdData"), ofPattern("d-MM-yyyy")))
                .dueData(LocalDate.parse(request.getParameter("dueData"), ofPattern("d-MM-yyyy")))
                .solvePriorityId(Integer.valueOf(request.getParameter("solvePriorityId")))
                .estimate(Integer.valueOf(request.getParameter("estimate")))
                .client(request.getParameter("client"))
                .build();
        maintDao.save(maint);
    }
}
