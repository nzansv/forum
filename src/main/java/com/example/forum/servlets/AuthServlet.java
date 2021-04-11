package com.example.forum.servlets;

import com.example.forum.beans.CommentBean;
import com.example.forum.beans.PostBean;
import com.example.forum.beans.UserBean;
import com.example.forum.dao.CommentDAO;
import com.example.forum.dao.PostDAO;
import com.example.forum.dao.UserDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(value = "/auth")
public class AuthServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PostDAO postDAO = new PostDAO();
        UserDAO userDao = new UserDAO();
        CommentDAO commentDAO = new CommentDAO();
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserBean user = null;
        PostBean postBean = null;
        try {
            postBean = postDAO.getPost(1);
            List<CommentBean> commentBeanList = commentDAO.getComments();

            for (CommentBean commentBean : commentBeanList){
                commentBean.setUserBean(userDao.getUser(commentBean.getUser_id()));
            }

            user = userDao.checkLogin(username,password);
            postBean.setComments(commentBeanList);
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setMaxInactiveInterval(30*60);
            Cookie userName = new Cookie("username", username);
            request.setAttribute("posts", postBean);
            userName.setMaxAge(30*60);
            response.addCookie(userName);

        } catch (SQLException | ClassNotFoundException ex) {
            throw new ServletException(ex);
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("home.jsp");
        requestDispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.getRequestDispatcher("auth.jsp");
    }

}
