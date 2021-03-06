package com.maint.manager.service.servlets;

import com.maint.manager.persistence.dao.MaintCommentDao;
import com.maint.manager.persistence.dao.MaintDao;
import com.maint.manager.persistence.entities.MaintComments;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.maint.manager.service.servlets.BasicServlet.entityManagerFactory;
import static java.lang.Long.parseLong;
import static java.lang.String.format;
import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static javax.servlet.http.HttpServletResponse.SC_CREATED;

@WebServlet("/comment")
public class PostCommentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final var maintCommentDao = new MaintCommentDao(entityManagerFactory);
        final var maintDao = new MaintDao(entityManagerFactory);

        final var comment = request.getParameter("comment");
        final var maintId = request.getParameter("maintId");

        try {
            final var maint = maintDao
                    .findById(parseLong(maintId))
                    .orElseThrow(() -> new IllegalStateException(format("Maint by Id: '%s' was not found !!", maintId)));

            final var maintComment = new MaintComments(comment, maint);

            maintCommentDao.save(maintComment);

            response.getWriter().write(format("New comment was added: '%s'", comment));

            response.setStatus(SC_CREATED);
        } catch (Exception e) {
            response.setStatus(SC_BAD_REQUEST);
            response.getWriter().write(format("Comment was not added due to: '%s'", e.getMessage()));
        }
    }
}
