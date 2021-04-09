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
}
