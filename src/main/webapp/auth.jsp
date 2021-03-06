<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
            <li class="nav-item active">
                <a class="nav-link" href="auth.jsp">Sign in <span class="sr-only">(current)</span> </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="register.jsp">Sign up</a>
            </li>
        </ul>
    </div>
</nav>
</div>


<br>
<br>
<div class="row mb-5 d-flex justify-content-center" >

    <div class="col-4 rounded p-4"  style="width: 400px; background: #F7F4F3" >
        <form action="auth" method="POST">
            <h4 class="d-flex justify-content-center">Login</h4>
            <div class="mb-3">
                <label class="form-label">Username:</label>
                <input type="text" name="username" class="form-control" >
            </div>
            <div class="mb-3">
                <label class="form-label">Password:</label>
                <input type="password" name="password" class="form-control"  >
            </div>
            <center>
                <div class="d-grid gap-2 d-md-block">
                    <button type="submit"  value="register" class="btn btn-outline-secondary" type="button">Sign in</button>
                </div>
            </center>
        </form>
    </div>
</div>
</div>
</div>
</body>
</html>
