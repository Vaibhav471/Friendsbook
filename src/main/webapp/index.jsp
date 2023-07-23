<%@page import="com.eschool.dao.DBHandler"%>
<%@page import="com.eschool.dao.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
.error{
color: red;
}
</style>

<script>
function isValid(v,cn)
{
	if(v.length==0){
		document.getElementById(cn).innerHTML= "can't leave it blank";
		
	}
	else{
		document.getElementById(cn).innerHTML= "";

	}
	
	}
	
</script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>

<%
Cookie[] cookies = request.getCookies();
if (cookies != null) {
    for (Cookie cookie : cookies) {
        if (cookie.getName().equals("u")) {
            String combinedValue = cookie.getValue();
            String[] parts = combinedValue.split(":");
            String uemail = parts[0];
            String upassword = parts[1];
            
            User u=new User(uemail,upassword,null);
            DBHandler db=new DBHandler();
            User u1=db.isValid(u);
            
            if(u1!=null)
    		{
    			session.setAttribute("user",u1);
    			session.setMaxInactiveInterval(60*60);
    	
            response.sendRedirect("welcome.jsp");
            }
            
            
            
        }
    }
}
%>


 <div class="jumbotron text-center" style="background-color: #3b5998; color: aliceblue;">
        <h1>Friendsbook</h1>
        <p>By Vaibhav Mishra</p>
      </div>
      
<div class="container">
        <div class="row">
          <div class="col-sm-6" style="padding-right: 60px;">
            <h3>Sign Up</h3><br>
            <form action="signup" method="post">
              <div class="form-group">
                <label for="email">Email address:</label>
                <input type="email" class="form-control" name="email" onblur="isValid(this.value, 'error_email1')">
                <div id="error_email1" class="error">  </div>
                
              </div>
              <div class="form-group">
                <label for="pwd">Password:</label>
                <input type="password" class="form-control" name="password" onblur="isValid(this.value, 'error_email2')">
                <div id="error_email2" class="error">  </div>
                
              </div>
              <div class="form-group">
                <label for="pwd">Name:</label>
                <input type="text" class="form-control" name="name" onblur="isValid(this.value, 'error_email3')">
                <div id="error_email3" class="error">  </div>
                
              </div>
              <button type="submit" class="btn btn-default" style="background-color: #4856CC; color: white;">Submit</button>


</form>          </div>

<div class="col-sm-6" style="padding-left: 60px;">
            <h3>Login</h3><br>
            <form action="login" method="post" style="margin-top: 20px;">
              <div class="form-group">
                <label for="email">Email address:</label>
                <input type="email" class="form-control" name="email"  onblur="isValid(this.value,'error_email4')">
                 <div id="error_email4" class="error">  </div>
                
              </div>
              <div class="form-group">
                <label for="pwd">Password:</label>
                <input type="password" class="form-control" name="password" onblur="isValid(this.value,'error_email5')">
                 <div id="error_email5" class="error">  </div>
                
              </div>
              <div class="checkbox">
                <label><input type="checkbox" name="checkbox">Remember me</label>
              </div>
              <button type="submit" class="btn btn-default" style="background-color: #4856CC; color: white;">Submit</button>
            </form><br>   
            <%
if(request.getAttribute("error_login")!=null)
{
	%>
	<font color="red"><%=request.getAttribute("error_login") %></font>
	<% 
}
%>
            
                      </div>
 </div>
      </div>
</body>
</html>