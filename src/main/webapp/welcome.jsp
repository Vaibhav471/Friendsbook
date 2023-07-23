<%@page import="com.eschool.dao.Wpost"%>
<%@page import="org.apache.catalina.tribes.ChannelSender"%>
<%@page import="com.eschool.dao.Friend"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.eschool.dao.DBHandler"%>
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
<body>

 <%if(session.getAttribute("user")!=null){
	 
    DBHandler db=new DBHandler();
    User user=(User)session.getAttribute("user");
    ArrayList<Friend> friends=db.getPendingRequest(user.getEmail());
    ArrayList<Friend> friends1=db.getFriends(user.getEmail());
    
    ArrayList<Wpost> wp=new ArrayList<>();
    
    for(Friend uf:friends1){
    	if(uf.getRec().equals(user.getEmail())){
    	ArrayList<Wpost> posts=db.getWposts(uf.getSender());
    	for(Wpost w:posts){
    		wp.add(w);
    	}
    	}
    	else{
    		ArrayList<Wpost> posts=db.getWposts(uf.getRec());
        	for(Wpost w:posts){
        		wp.add(w);
        	}
    	}

    }
    
    %>
    <header style="    display: flex; align-items: center; background-color: #3b5998; padding: 10px 20px; color: white;">
		<div style="flex: 1">
			<h1>Friendsbook</h1>
		</div>
		<form action="sendrequest" method="post" style="flex: 2; display: flex; align-items: center;">
          <input type="email" class="form-control" placeholder="Enter Friend's Email" style="display: inline; max-width: 300px" name="r_email"/>
          <button type="submit" class="btn btn-default" style="background-color: #4856CC; color: white;">Send Request</button>
          
          
          </form> 
          
          <div style=" margin-right: 30px;"> <a href="profile.jsp?po=<%=user.getEmail()%>">
          <%if(db.getPphoto(user.getEmail()).equals("")){ %>		
          <img src="user.jpg" class="img-circle" width="50" height="50" style="display: inline;"/>
          <%}
          else{%>
          <img src="data:image/jpg;base64,<%=db.getPphoto(user.getEmail())%>" class="img-circle" width="50" height="50" style="display: inline;"/>
          
          <%} %>
                        &nbsp;&nbsp; <h3 style="color: white; display: inline;"><%=user.getName()%></h3></a>
           </div>
	</header>
    
      
  
  
    <div class="row" style="margin-top: 10px">
      <div class="col-sm-3" style="text-align: left;">
      <div style="padding-left: 30px">
        <h2 style="padding-right: 20px; font-weight: bold;">Requests</h2>
        <ul class="list-group">
 <% for(Friend friend:friends){
	 %>
	 
	<li class="list-group-item"> 
	<div style="text-align: left;"> 
	<%if(db.getPphoto(friend.getSender()).equals("")) {%>
		<img src="user.jpg" class="img-circle" width="50" height="50"/>
	<%}
	else{%>
		<img src="data:image/jpg;base64,<%=db.getPphoto(friend.getSender())%>" class="img-circle" width="50" height="50"/>
	
	<%} %>
	&nbsp;&nbsp;&nbsp;<h4 style="display: inline;"> <%=db.getName(friend.getSender())%></h4><div style="text-align: right; display: inline;">&nbsp;&nbsp;&nbsp;<a href="accept?fid=<%=friend.getFid()%>"><div><button type="button" class="btn btn-default" style="background-color: #4856CC; color: white;">Accept</button></a>&nbsp;<a href="reject?fid=<%=friend.getFid()%>"><button type="button" class="btn btn-default" style="background-color: #4856CC; color: white;">Reject</button></a></div></li><br>
 <%

 }
 %>
 </ul>
 </div>
      </div>

      <div class="col-sm-6" style="text-align: center; padding-top: 30px; background-color: #C9BFFF">
              <form action="savepost" method="post" enctype="multipart/form-data">
<textarea   type="text" name="postcontent" rows="6" cols="70" placeholder="What's on your mind, <%=user.getName()%>?">
</textarea><br>  
       <button type="submit" class="btn" style="width: 200px ;background-color: #4856CC; margin-left: 100px; color: white; display: inline;">Post</button>
  <input type="file" name="photo" style="display: inline;" />
        </form>
        <div  style="margin-top: 20px">
        <ul class="list-group" style="max-height: 450px; overflow: scroll">
        
        <%for(Wpost w1:wp){
        	
        	
        	if(w1.getRetrieve_photo().equals("")){
        %>
        
        <li class="list-group-item">
        <div style="text-align: left;">
                <a href="profile.jsp?po=<%=w1.getSender()%>">
        
        <%if(db.getPphoto(w1.getSender()).equals("")){ %>
                   <img src="user.jpg" class="img-circle" width="25" height="25">
        <%}
        
        else{%>
                <img src="data:image/jpg;base64,<%=db.getPphoto(w1.getSender())%>" class="img-circle" width="25" height="25"/>
        
        
        <%} %>
        &nbsp;&nbsp;<P style="display: inline;"><%=db.getName(w1.getSender())%></P></a></div><br>
        <h4><%=w1.getPostcontent()%></h4> <br>
        
        <p style="text-align: right"><%=w1.getDop()%></p>  </li> <br>
        
        
        <%}
        	
        	
        else{%>
         <li class="list-group-item">
        <div style="text-align: left;">
                <a href="profile.jsp?po=<%=w1.getSender()%>">
        
        <%if(db.getPphoto(w1.getSender()).equals("")){ %>
                   <img src="user.jpg" class="img-circle" width="25" height="25">
        <%}
        else{%>
                <img src="data:image/jpg;base64,<%=db.getPphoto(w1.getSender())%>" class="img-circle" width="25" height="25"/>
        
        
        
        <%} %>
        &nbsp;&nbsp;<p style="display: inline;"><%=db.getName(w1.getSender())%></p></a></div><br>
        <h4><%=w1.getPostcontent()%></h4> <br>
          <img src="data:image/jpg;base64,<%=w1.getRetrieve_photo()%>" width="550" height="300"/ style="text-align: center;"><br><br>
        <p style="text-align: right"><%=w1.getDop()%></p>  </li> <br>
        
        <%} }%>
        </ul>
        </div>
      </div>
      <div class="col-sm-3">
<div style="padding-right: 30px">
        <h2 style="padding-left: 20px; font-weight: bold;">Friends</h2>
        <ul class="list-group">  <% for(Friend f:friends1){
	 
	 if(f.getRec().equals(user.getEmail())){
	 
%>	 
<li class="list-group-item"><a href="profile.jsp?po=<%=f.getSender()%>">
<%if(db.getPphoto(f.getSender()).equals("")){ %>
		<img src="user.jpg" class="img-circle" width="50" height="50"/>
<%}
	else{%>
	<img src="data:image/jpg;base64,<%=db.getPphoto(f.getSender())%>" class="img-circle" width="50" height="50" style="display: inline;"/>
	
<%} %>
&nbsp;&nbsp;&nbsp;<h4 style="display: inline;"><%=db.getName(f.getSender())%></h4></a></li><br>

<%}else {%>
<li class="list-group-item"><a href="profile.jsp?po=<%=f.getRec()%>">
<%if(db.getPphoto(f.getRec()).equals("")){ %>
		<img src="user.jpg" class="img-circle" width="50" height="50"/>
<%}
	else{%>
	<img src="data:image/jpg;base64,<%=db.getPphoto(f.getRec())%>" class="img-circle" width="50" height="50" style="display: inline;"/>
	
<%} %>&nbsp;&nbsp;&nbsp;<h4 style="display: inline;"><%=db.getName(f.getRec())%></h4></a></li><br>
		 

<%} }%>
	<%} %>
	</ul>
 </div>
      </div>
    </div>
    
</body>
</html>