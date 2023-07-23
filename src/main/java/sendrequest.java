

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eschool.dao.DBHandler;
import com.eschool.dao.Friend;
import com.eschool.dao.User;

@WebServlet("/sendrequest")
public class sendrequest extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		User user=(User)session.getAttribute("user");
		String sender=user.getEmail();
		String rec=request.getParameter("r_email");
		Date d=new Date();
		String dor=(d.getYear()+1900)+"-"+(d.getMonth()+1)+"-"+(d.getDate());
		Friend friend=new Friend(0,0,sender,rec,dor);
		DBHandler db=new DBHandler();
		db.save(friend);
		response.sendRedirect("welcome.jsp");
	}

}
