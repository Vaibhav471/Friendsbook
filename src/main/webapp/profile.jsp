<%@page import="com.eschool.dao.User"%>
<%@page import="com.eschool.dao.Pphoto"%>
<%@page import="com.eschool.dao.Wpost"%>
<%@page import="com.eschool.dao.DBHandler"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body style="background-color: #4856CC">
<%
User user=(User)session.getAttribute("user");
String po="";
if(request.getParameter("po")!=null){
po=request.getParameter("po");
} else{
po=user.getEmail();
}
DBHandler db=new DBHandler();
ArrayList<Wpost> posts=db.getWposts(po);

%>
<div class="container" style=" background-color: #C9BFFF; min-height: 1000px;">

<div style="background-color: white; padding: 20px; margin-top: 30px">


<div class="row">
    <div class="col-sm-4">
     <%if(db.getPphoto(po).equals("")){ %>

<img src="user.jpg" class="img-circle" width="200" height="200">



<%}
else{%>
	
<img src="data:image/jpg;base64,<%=db.getPphoto(po)%>" class="img-circle" width="200" height="200">

<%} 

 if(po.equals(user.getEmail())){
%>
    <form action="savepphoto" method="post" enctype="multipart/form-data" style="margin-top: 15px;">
  <input type="file" name="pic" style="display: inline;" /><br>
   <button type="submit" class="btn" style="width: 200px ;background-color: #4856CC; margin-left: 100px; color: white; display: inline; margin-left: 1px">Update profile picture</button>
  </form>
  
  
  <%} %>
    </div>
    <div class="col-sm-4">
      <h3><%=db.getName(po) %></h3><br>
      <h3 style="display: inline;">Email</h3>&nbsp; <p style="display: inline; font-weight: bold;"><%=po %></p>
      
    </div>
    <div class="col-sm-4" style="text-align: right;">
    <% if(po.equals(user.getEmail())){
 %>
      <a href="edit.jsp"><button type="button" class="btn btn-default" style="background-color: #4856CC; color: white;">Edit Profile</button>
</a>
<%} %>
    </div>
  </div>


</div>

<ul class="list-group" style="max-width: 600px;  margin-left: auto; margin-right: auto; margin-top: 20px">

<%for(Wpost post:posts){
	if(post.getRetrieve_photo().equals("")){
%>

<li class="list-group-item"><h4><%=post.getPostcontent()%></h4> <p style="text-align: right"><%=post.getDop()%></p>  </li> <br>
<%} 

 else { 
	 String photo=post.getRetrieve_photo();
 
 %>
  <li class="list-group-item">       
  <h4><%=post.getPostcontent()%></h4><br>
  <div style="text-align: center;">
  <img src="data:image/jpg;base64,<%=photo%>" width="550" height="300"/>
  </div><br>
   <p style="text-align: right"><%=post.getDop()%></p>  
  </li> <br>


<%
  
 }
 }
%>
</ul>
</div>

</body>
</html>