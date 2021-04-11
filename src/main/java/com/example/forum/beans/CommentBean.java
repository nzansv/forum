package com.example.forum.beans;

public class CommentBean implements java.io.Serializable {
    private Integer id;
    private int like_counter = 0;
    private String content = null;
    private Integer user_id;
    private Integer post_id;
    private UserBean userBean;

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public Integer getPost_id() {
        return post_id;
    }

    public void setPost_id(Integer post_id) {
        this.post_id = post_id;
    }

    public CommentBean() {}

    public CommentBean(Integer id, int like_counter, String content, Integer user_id, Integer post_id) {
        this.id = id;
        this.like_counter = like_counter;
        this.content = content;
        this.user_id = user_id;
        this.post_id = post_id;
    }

    public CommentBean(int like_counter, String content, Integer user_id, Integer post_id) {
        this.like_counter = like_counter;
        this.content = content;
        this.user_id = user_id;
        this.post_id = post_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public int getLike_counter() {
        return like_counter;
    }

    public void setLike_counter(final int like_counter) {
        this.like_counter = like_counter;
    }

    public String getContent() {
        return content;
    }


    public void setContent(final String content) {
        this.content = content;
    }
}

