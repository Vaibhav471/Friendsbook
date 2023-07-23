

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eschool.dao.DBHandler;
import com.eschool.dao.Friend;

@WebServlet("/reject")
public class reject extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int fid=Integer.parseInt(request.getParameter("fid"));
		Friend friend=new Friend(fid);
		DBHandler db=new DBHandler();
		
		db.reject(friend);
		response.sendRedirect("welcome.jsp");
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
