<%@page import="com.eschool.dao.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body style="background-color: #4856CC">
<% User user=(User)session.getAttribute("user");
%>
<div class="container" style=" background-color: white; min-height: 1000px">
<h1 style="text-align: center;">Update Profile</h1>
<form action="updateprofile" method="post" style="padding: 300px">
<input class="form-control" type="text" name="email" value="<%=user.getEmail()%>" readonly="readonly"/><br>
<input class="form-control" type="text" name="password" value="<%=user.getPassword()%>"/><br>
<input class="form-control" type="text" name="name" value="<%=user.getName()%>"/><br>

<button type="submit" class="btn btn-default" style="background-color: #4856CC; color: white;">Update</button>
</form>
</div>
</body>
</html>