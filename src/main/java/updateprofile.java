

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eschool.dao.DBHandler;
import com.eschool.dao.User;

@WebServlet("/updateprofile")
public class updateprofile extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		String name=request.getParameter("name");
		User user=new User(email, password, name);
		DBHandler db=new DBHandler();
		db.update(user);
		HttpSession session=request.getSession();
		session.setAttribute("user",user);
		response.sendRedirect("profile.jsp");
	}

}
