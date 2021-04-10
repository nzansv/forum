package com.example.forum.servlets;

import com.example.forum.beans.CommentBean;
import com.example.forum.dao.CommentDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "CommentServlet", value = "/CommentServlet")
public class CommentServlet extends HttpServlet {
    CommentDAO commentDAO = new CommentDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String content = request.getParameter("content");
        int like_counter = Integer.parseInt(request.getParameter("like_counter"));
        int user_id = Integer.parseInt(request.getParameter("user_id"));
        int post_id = Integer.parseInt(request.getParameter("post_id"));

        CommentBean commentBean = new CommentBean(like_counter,content,user_id,post_id);
        try {
            commentDAO.insertCourse(commentBean);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        response.sendRedirect("home.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
