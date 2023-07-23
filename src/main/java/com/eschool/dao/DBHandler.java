package com.eschool.dao;
import java.io.ByteArrayInputStream;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
public class DBHandler {
	String error;

	public DBHandler() {
		error="";
	}
	
	
public void update(User user) {
	Connection connection=null;
	
	try {
		 connection=getConnection();
		String query="update user set password=?,name=? where email=?";
		PreparedStatement statement=connection.prepareStatement(query);
		statement.setString(1,user.getPassword());
		statement.setString(2,user.getName());
		statement.setString(3,user.getEmail());

		statement.executeUpdate();


		
	}
	catch (Exception e) {
		error=e.getMessage();
	}
	finally {
		try {
			connection.close();

		}
		catch (SQLException e){
			e.printStackTrace();
			
		}
		
	}
	
}
public void accept(Friend friend) {
	Connection connection=null;
	
	try {
		 connection=getConnection();
		String query="update friends set status=1 where fid=?";
		PreparedStatement statement=connection.prepareStatement(query);
		statement.setInt(1,friend.getFid());

		

		statement.executeUpdate();


		
	}
	catch (Exception e) {
		error=e.getMessage();
	}
	finally {
		try {
			connection.close();

		}
		catch (SQLException e){
			e.printStackTrace();
			
		}
		
	}
	
}
public void reject(Friend friend) {
	Connection connection=null;
	
	try {
		 connection=getConnection();
		String query="update friends set status=2 where fid=?";
		PreparedStatement statement=connection.prepareStatement(query);
		statement.setInt(1,friend.getFid());

		

		statement.executeUpdate();


		
	}
	catch (Exception e) {
		error=e.getMessage();
	}
	finally {
		try {
			connection.close();

		}
		catch (SQLException e){
			e.printStackTrace();
			
		}
		
	}
	
}
public void save(User user) {
	Connection connection=null;
	
	try {
		 connection=getConnection();
		String query="insert into user values(?,?,?)";
		PreparedStatement statement=connection.prepareStatement(query);
		statement.setString(1,user.getEmail());
		statement.setString(2,user.getPassword());
		statement.setString(3,user.getName());
		statement.executeUpdate();


		
	}
	catch (Exception e) {
		error=e.getMessage();
	}
	finally {
		try {
			connection.close();

		}
		catch (SQLException e){
			e.printStackTrace();
			
		}
		
	}
	
}
public void save(Friend friend) {
	Connection connection=null;
	
	try {
		 connection=getConnection();
		String query="insert into friends(sender, rec, status, dor) values(?,?,?,?)";
		PreparedStatement statement=connection.prepareStatement(query);
		statement.setString(1,friend.getSender());
		statement.setString(2,friend.getRec());
		statement.setInt(3,friend.getStatus());
		statement.setString(4,friend.getDor());

		statement.executeUpdate();


		
	}
	catch (Exception e) {
		error=e.getMessage();
	}
	finally {
		try {
			connection.close();

		}
		catch (SQLException e){
			e.printStackTrace();
			
		}
		
	}
	
}
public void savePost(Wpost post) {
	Connection connection=null;
	
	try {
		 connection=getConnection();
		String query="insert into wpost(sender, postcontent, dop, postimage) values(?,?,?,?)";
		PreparedStatement statement=connection.prepareStatement(query);
		statement.setString(1,post.getSender());
		statement.setString(2,post.getPostcontent());
		statement.setString(3,post.getDop());
		statement.setBlob(4, post.getPhoto());
		


		statement.executeUpdate();


		
	}
	catch (Exception e) {
		error=e.getMessage();
	}
	finally {
		try {
			connection.close();

		}
		catch (SQLException e){
			e.printStackTrace();
			
		}
		
	}
	
}

public void savePphoto(Pphoto pic) {
	Connection connection=null;
	
	try {
		 connection=getConnection();
		String query="insert into pphoto(sender, pic, doup) values(?,?,?)";
		PreparedStatement statement=connection.prepareStatement(query);
		statement.setString(1,pic.getSender());
		statement.setBlob(2,pic.getPic_upload());
		statement.setString(3,pic.getDoup());
		


		statement.executeUpdate();


		
	}
	catch (Exception e) {
		error=e.getMessage();
	}
	finally {
		try {
			connection.close();

		}
		catch (SQLException e){
			e.printStackTrace();
			
		}
		
	}
	
}


Connection getConnection() {
	Connection connection=null;
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection=DriverManager.getConnection("jdbc:mysql://localhost/fb?user=root&password=root");
		
	}
	catch(Exception e) {
		error=e.getMessage();
	}
	return connection;
}
public String getError() {
	return error;
}

public User isValid(User user) {
	User u=null;
	Connection connection=null;
	
	try {
		 connection=getConnection();
		String query="select * from user where email=? and password=?";
		PreparedStatement statement=connection.prepareStatement(query);
		statement.setString(1,user.getEmail());
		statement.setString(2,user.getPassword());
		ResultSet result=statement.executeQuery();
		if(result.next()) {
			u=new User(result.getString(1),result.getString(2),result.getString(3));
		}


		
	}
	catch (Exception e) {
		error=e.getMessage();
	}
	finally {
		try {
			connection.close();

		}
		catch (SQLException e){
			e.printStackTrace();
			
		}
		
	}
	return u;
	
	
}
public String getName(String email) {
	String name="";
	Connection connection=null;
	
	try {
		 connection=getConnection();
		String query="select name from user where email=?";
		PreparedStatement statement=connection.prepareStatement(query);
		statement.setString(1,email);
		ResultSet result=statement.executeQuery();
	result.next();
		name=result.getString(1);
	}
	catch (Exception e) {
		error=e.getMessage();
	}
	finally {
		try {
			connection.close();

		}
		catch (SQLException e){
			e.printStackTrace();
			
		}
		
	}
	return name;
}

public ArrayList<Friend> getPendingRequest(String rec) {
	ArrayList<Friend> friends=new ArrayList<>();
	Connection connection=null;
	
	try {
		 connection=getConnection();
		String query="select * from friends where rec=? and status=0 order by dor desc";
		PreparedStatement statement=connection.prepareStatement(query);
		statement.setString(1,rec);
		ResultSet result=statement.executeQuery();
		while(result.next()) {
			int fid=result.getInt(1);
			String sender=result.getString(2);
			String rec_1=result.getString(3);
			int status=result.getInt(4);
			String dor=result.getString(5);
			Friend friend=new Friend(fid, status, sender, rec_1, dor);
			friends.add(friend);
			
			
		}


		
	}
	catch (Exception e) {
		error=e.getMessage();
	}
	finally {
		try {
			connection.close();

		}
		catch (SQLException e){
			e.printStackTrace();
			
		}
		
	}
	return friends;
	
}

public ArrayList<Wpost> getWposts(String po) {
	ArrayList<Wpost> wposts=new ArrayList<>();
	Connection connection=null;
	String retrive_photo="";
	
	try {
		 connection=getConnection();
		String query="select * from wpost where sender=? order by pid desc";
		PreparedStatement statement=connection.prepareStatement(query);
		statement.setString(1,po);

		ResultSet result=statement.executeQuery();
		while(result.next()) {
			int pid=result.getInt(1);
			String sender=result.getString(2);
			String postcontent=result.getString(3);
			String dop=result.getString(4);
			 Blob blob = result.getBlob(5); 
			 
			 if(blob!=null) {
             InputStream inputStream = blob.getBinaryStream();
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
             byte[] buffer = new byte[4096];
             int bytesRead = -1;                 
             while ((bytesRead = inputStream.read(buffer)) != -1) {
                 outputStream.write(buffer, 0, bytesRead);                  
             }                 
             byte[] imageBytes = outputStream.toByteArray();
             retrive_photo = Base64.getEncoder().encodeToString(imageBytes);
             inputStream.close();
             outputStream.close();      
			 }
			Wpost wpost=new Wpost(pid, sender, postcontent, dop, retrive_photo);
			wposts.add(wpost);
			
			
		}


		
	}
	catch (Exception e) {
		error=e.getMessage();
	}
	finally {
		try {
			connection.close();

		}
		catch (SQLException e){
			e.printStackTrace();
			
		}
		
	}
	
	return wposts;
	
}

public String getPphoto(String pe) {
	Connection connection=null;
	String retrive_photo="";
	
	try {
		 connection=getConnection();
		String query="select pic from pphoto where sender=? order by pid desc limit 1";
		PreparedStatement statement=connection.prepareStatement(query);
		statement.setString(1,pe);

		ResultSet result=statement.executeQuery();
		result.next();
			 Blob blob = result.getBlob(1);

			 
			 if(blob!=null) {
             InputStream inputStream = blob.getBinaryStream();
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
             byte[] buffer = new byte[4096];
             int bytesRead = -1;                 
             while ((bytesRead = inputStream.read(buffer)) != -1) {
                 outputStream.write(buffer, 0, bytesRead);                  
             }                 
             byte[] imageBytes = outputStream.toByteArray();
             retrive_photo = Base64.getEncoder().encodeToString(imageBytes);
             inputStream.close();
             outputStream.close();      
			 }
			
			
			
		


		
	}
	catch (Exception e) {
		error=e.getMessage();
	}
	finally {
		try {
			connection.close();

		}
		catch (SQLException e){
			e.printStackTrace();
			
		}
		
	}
	
	return retrive_photo;
	
}

public ArrayList<Pphoto> getPphotos(String pe) {
	ArrayList<Pphoto> photos=new ArrayList<>();
	Connection connection=null;
	String retrive_photo="";
	
	try {
		 connection=getConnection();
		String query="select * from Pphoto where sender=? order by pid desc";
		PreparedStatement statement=connection.prepareStatement(query);
		statement.setString(1,pe);

		ResultSet result=statement.executeQuery();
		while(result.next()) {
			int pid=result.getInt(1);
			String sender=result.getString(2);
			 Blob blob = result.getBlob(3);
				String doup=result.getString(4);

			 
			 if(blob!=null) {
             InputStream inputStream = blob.getBinaryStream();
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
             byte[] buffer = new byte[4096];
             int bytesRead = -1;                 
             while ((bytesRead = inputStream.read(buffer)) != -1) {
                 outputStream.write(buffer, 0, bytesRead);                  
             }                 
             byte[] imageBytes = outputStream.toByteArray();
             retrive_photo = Base64.getEncoder().encodeToString(imageBytes);
             inputStream.close();
             outputStream.close();      
			 }
			Pphoto photo=new Pphoto(pid, sender, retrive_photo, doup);
			photos.add(photo);
			
			
		}


		
	}
	catch (Exception e) {
		error=e.getMessage();
	}
	finally {
		try {
			connection.close();

		}
		catch (SQLException e){
			e.printStackTrace();
			
		}
		
	}
	
	return photos;
	
}

public ArrayList<Friend> getFriends(String usr) {
	ArrayList<Friend> friends=new ArrayList<>();
	Connection connection=null;
	
	try {
		 connection=getConnection();
		String query="select * from friends where (rec=? or sender=?) and status=1 order by dor desc";
		PreparedStatement statement=connection.prepareStatement(query);
		statement.setString(1,usr);
		statement.setString(2,usr);

		ResultSet result=statement.executeQuery();
		while(result.next()) {
			int fid=result.getInt(1);
			String sender=result.getString(2);
			String rec_1=result.getString(3);
			int status=result.getInt(4);
			String dor=result.getString(5);
			Friend friend=new Friend(fid, status, sender, rec_1, dor);
			friends.add(friend);
			
			
		}


		
	}
	catch (Exception e) {
		error=e.getMessage();
	}
	finally {
		try {
			connection.close();

		}
		catch (SQLException e){
			e.printStackTrace();
			
		}
		
	}
	
	return friends;}
	
}

