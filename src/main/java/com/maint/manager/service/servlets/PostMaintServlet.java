package com.maint.manager.service.servlets;

import com.maint.manager.persistence.dao.MaintCommentDao;
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
import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static javax.servlet.http.HttpServletResponse.SC_CREATED;

@WebServlet("/maint")
public class PostMaintServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final var maintDao = new MaintDao(entityManagerFactory);

        final var comment = request.getParameter("comment");

        try {
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

            maintDao.save(maint);

            if (comment != null && !comment.isEmpty()) {
                final var maintCommentDao = new MaintCommentDao(entityManagerFactory);
                final var maintComment = new MaintComments(comment, maint);
                maintCommentDao.save(maintComment);

                response.getWriter().write(format("New maint was added: '%s' with comment: %s", maint, maintComment));

                return;
            }

            response.setStatus(SC_CREATED);
            response.getWriter().write(format("New maint was added: '%s'", maint));
        } catch (Exception e) {
            response.setStatus(SC_BAD_REQUEST);
            response.getWriter().write(format("Maint was not added due to: '%s'", e.getMessage()));
        }
    }
}
