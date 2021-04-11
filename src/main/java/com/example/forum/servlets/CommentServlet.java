package com.example.forum.servlets;

import com.example.forum.beans.CommentBean;
import com.example.forum.beans.PostBean;
import com.example.forum.dao.CommentDAO;
import com.example.forum.dao.PostDAO;
import com.example.forum.dao.UserDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "CommentServlet", value = "/")
public class CommentServlet extends HttpServlet {
    CommentDAO commentDAO = new CommentDAO();
    PostDAO postDAO = new PostDAO();
    UserDAO userDao = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/commentList":
                    commentList(request, response);
                    break;
                case "/addComment":
                    addComment(request, response);
                    break;
                case "/like":
                    likeComment(request, response);
                    break;

            }
        } catch (SQLException | ClassNotFoundException ex) {
            throw new ServletException(ex);
        }
    }

    private void addComment(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ClassNotFoundException {
        String content = request.getParameter("content");
        int like_counter = Integer.parseInt(request.getParameter("like_counter"));
        int user_id = Integer.parseInt(request.getParameter("user_id"));
        int post_id = Integer.parseInt(request.getParameter("post_id"));

        CommentBean commentBean = new CommentBean(like_counter, content, user_id, post_id);
        commentDAO.insertCourse(commentBean);
        response.sendRedirect("commentList");
    }

    private void likeComment(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ClassNotFoundException, ServletException {

        Boolean isLiked = Boolean.valueOf(req.getParameter("like"));
        Integer commentId = Integer.parseInt(req.getParameter("commentId"));

        try {
            CommentBean comment = commentDAO.getCommentById(commentId);
            if (isLiked)
                comment.setLike_counter(comment.getLike_counter() + 1);
            commentDAO.likeComment(comment);

            RequestDispatcher requestDispatcher = req.getRequestDispatcher("commentList");
            requestDispatcher.forward(req, resp);
        } catch (SQLException | ClassNotFoundException | ServletException ex) {
            throw new ServletException(ex);
        }
    }

    private void commentList(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        try {
            PostBean postBean = null;

            postBean = postDAO.getPost(1);
            String destPage = "home.jsp";
            List<CommentBean> commentBeanList = commentDAO.getComments();

            for (CommentBean commentBean : commentBeanList) {
                commentBean.setUserBean(userDao.getUser(commentBean.getUser_id()));
            }
            postBean.setComments(commentBeanList);
            request.setAttribute("posts", postBean);
            RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
            dispatcher.forward(request, response);

        } catch (SQLException | ClassNotFoundException ex) {
            throw new ServletException(ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
