package com.maint.manager.service.servlets;

import com.maint.manager.persistence.dao.MaintDao;
import com.maint.manager.persistence.entities.Maint;
import com.maint.manager.persistence.entities.MaintComments;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

import static com.maint.manager.service.servlets.BasicServlet.entityManagerFactory;
import static java.lang.String.format;
import static java.time.format.DateTimeFormatter.ofPattern;

@WebServlet("/maint")
public class PostMaintServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final var maintDao = new MaintDao(entityManagerFactory);

        final var comment = request.getParameter("comment");

        final var maint = Maint.builder()
                .maintIdentifier(request.getParameter("maintIdentifier"))
                .capabilityId(Long.valueOf(request.getParameter("capabilityId")))
                .createdData(LocalDate.now())
                .dueData(LocalDate.parse(request.getParameter("dueData"), ofPattern("d-MM-yyyy")))
                .solvePriorityId(Integer.valueOf(request.getParameter("solvePriorityId")))
                .estimate(Integer.valueOf(request.getParameter("estimate")))
                .client(request.getParameter("client"))
                .fixVersion(request.getParameter("fixVersion"))
                .build();

        //TODO investigate null pointer exception in case of existing comment
        if (comment != null && !comment.isEmpty())
            maint.addComment(new MaintComments(comment, maint));

        maintDao.save(maint);

        response.getWriter().write(format("New maint was added: '%s'", maint));
    }
}
