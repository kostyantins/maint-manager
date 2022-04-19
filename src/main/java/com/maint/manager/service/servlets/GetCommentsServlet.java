package com.maint.manager.service.servlets;

import com.maint.manager.persistence.dao.MaintCommentDao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.maint.manager.service.servlets.BasicServlet.entityManagerFactory;

@WebServlet("/comments")
public class GetCommentsServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final var maintCommentDao = new MaintCommentDao(entityManagerFactory);
        final var commentsArray = maintCommentDao.findAll();
        final var writer = response.getWriter();

        if (!commentsArray.isEmpty()) {
            writer.println(commentsArray);
        } else writer.println("No active Maints present !!");
    }
}
