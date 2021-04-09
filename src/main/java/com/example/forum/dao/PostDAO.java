package com.example.forum.dao;

import com.example.forum.beans.PostBean;

import java.sql.*;

public class PostDAO {
    public PostBean getPost(Integer id) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");

        PostBean postBean = null;
        String sql = "SELECT * FROM posts WHERE id = ?";

        Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/forum","postgres", "123");

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String content = resultSet.getString("content");
            int like_counter = resultSet.getInt("like_counter");
            int user_id = resultSet.getInt("user_id");

            postBean = new PostBean(id, like_counter, content, user_id);
        }

        resultSet.close();
        statement.close();

        return postBean;
    }
}
