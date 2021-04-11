package com.example.forum.dao;

import com.example.forum.beans.CommentBean;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentDAO {
    public List<CommentBean> getComments() throws SQLException, ClassNotFoundException {
        List<CommentBean> commentBeanList = new ArrayList<>();
        Class.forName("org.postgresql.Driver");

        String sql = "SELECT * FROM comments";
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/forum","postgres", "123");

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String content = resultSet.getString("content");
            Integer user_id = resultSet.getInt("user_id");
            Integer post_id = resultSet.getInt("post_id");
            int like_counter = resultSet.getInt("like_counter");

            CommentBean commentBean = new CommentBean();
            commentBean.setContent(content);
            commentBean.setId(id);
            commentBean.setLike_counter(like_counter);
            commentBean.setPost_id(post_id);
            commentBean.setUser_id(user_id);
           commentBeanList.add(commentBean);
        }

        resultSet.close();
        statement.close();
        connection.close();

        return commentBeanList;
    }

    public boolean likeComment(CommentBean comment) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE comments SET like_counter = ? WHERE id = ?";
        Class.forName("org.postgresql.Driver");

        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/forum","postgres", "123");

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, comment.getLike_counter());
        statement.setInt(2, comment.getId());

        boolean liked = statement.executeUpdate() > 0;
        statement.close();
        connection.close();

        return liked;
    }

    public CommentBean getCommentById(Integer id) throws SQLException, ClassNotFoundException {

        UserDAO userDao = new UserDAO();

        String sql = "SELECT * FROM comments WHERE id = ? LIMIT 1";
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/forum","postgres", "123");


        CommentBean comment = new CommentBean();

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();
        resultSet.next();

        comment.setId(id);
        comment.setUser_id(resultSet.getInt("user_id"));
        comment.setContent(resultSet.getString("content"));
        comment.setLike_counter(resultSet.getInt("like_counter"));
        comment.setPost_id(resultSet.getInt("post_id"));

        return comment;
    }

    public boolean insertCourse(CommentBean commentBean) throws SQLException, ClassNotFoundException {

        Class.forName("org.postgresql.Driver");

        String sql = "INSERT INTO comments (content, like_counter, user_id, post_id ) VALUES (?, ?, ?, ?)";
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/forum","postgres", "123");

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, commentBean.getContent());
        statement.setInt(2, commentBean.getLike_counter());
        statement.setInt(3, commentBean.getUser_id());
        statement.setInt(4, commentBean.getPost_id());

        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        connection.close();;
        return rowInserted;
    }
}
