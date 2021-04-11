<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.forum.beans.CommentBean" %>
<%@ page import="com.example.forum.beans.UserBean" %>
<!DOCTYPE html>
<!DOCTYPE html>
<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">Navbar</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item bg-light">
                <form action="SignOutServlet" method="post">
                    <input class="btn" type="submit" value="Sign Out" >
                </form>
            </li>
        </ul>
    </div>
</nav>
</div>
<br>
<br>

<div class="row" style="margin-left: 30px">
    <div class="span8">
        <div class="row">
            <div class="span8">
                <h4><strong><a href="#">Title of the post</a></strong></h4>
            </div>
        </div>
        <div class="row">
            <div class="span6">
                <p>
                </p>
            </div>
        </div>
        <div class="row">
            <div class="span8">
                <p>${posts.content}</p>
                <p>
                    <i class="icon-user"></i> by <a href="#">${user.username}</a>
                    | <i class="icon-calendar"></i> April 09th, 2021
                    | <i class="icon-comment"></i> <a href="#">${posts.like_counter} Likes</a>
                </p>
            </div>
        </div>
    </div>
</div>
<hr>
<h4>Comments</h4>

<jsp:useBean id="posts" class="com.example.forum.beans.PostBean" scope="request"/>
<jsp:setProperty name="posts" property="*"/>
<jsp:useBean id="user" class="com.example.forum.beans.UserBean" scope="session"/>
<jsp:setProperty name="user" property="*"/>
<% for (CommentBean comment: posts.getComments()) { %>
<div class="card mb-3">
    <div class="card-body">
        <p class="card-title"><a href="#"><%=comment.getUserBean().getUsername()%></a></p>
        <p class="card-text"><%=comment.getContent()%></p>
        <form action="like" method="get">
            <input type="hidden" name="commentId" value="<%=comment.getId()%>">
            <button type="submit" class="btn btn-outline-primary" name="like" value="true" class="btn">Likes <%=comment.getLike_counter()%></button>
        </form>
    </div>
</div>

<% } %>


<% if(user.getId()!=null) {%>
<hr>
<h4>Add Comment</h4>
    <div class="card mb-3">
        <form action="addComment" method="post">
            <div class="mb-3">
                <input style="font-size: 18px" type="text" name="content" class="form-control" placeholder="write something..." >
            </div>
            <input name="like_counter" type="hidden" value="0" >
            <input name="user_id" type="hidden" value="${user.id}" >
            <input name="post_id" type="hidden" value="1" >
            <center>
                <div class="d-grid gap-2 d-md-block">
                    <button type="submit"  value="Save" class="btn"  style="background: #F5F5F5;font-size: 11px; padding: 15px 32px 15px 32px;" >add comment</button>
                </div>
            </center>
        </form>
    </div>
<% }%>
</body>
</html>

