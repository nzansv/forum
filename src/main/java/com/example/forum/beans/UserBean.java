package com.example.forum.beans;


public class UserBean implements java.io.Serializable {
    private Integer id;
    private String username = null;
    private String password = null;
    public UserBean() {}

    public UserBean( String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserBean(Integer id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getUsername() { return username; }

    public void setUsername(final String value) { username = value; }
}
