package com.example.forum.dao;

import com.example.forum.beans.UserBean;

import java.sql.*;

public class UserDAO {
    public UserBean checkLogin(String username, String password) throws SQLException,
            ClassNotFoundException {

        Class.forName("org.postgresql.Driver");
        Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/forum","postgres", "123");
        String sql = "SELECT * FROM users WHERE username = ? and password = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, username);
        statement.setString(2, password);

        ResultSet result = statement.executeQuery();

        UserBean user = null;

        if (result.next()) {
            user = new UserBean();
            user.setId(result.getInt("id"));
            user.setUsername(result.getString("username"));
        }

        conn.close();

        return user;
    }
}
