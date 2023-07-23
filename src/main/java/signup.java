

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eschool.dao.DBHandler;
import com.eschool.dao.User;

@WebServlet("/signup")
public class signup extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		String name=request.getParameter("name");
		User user=new User(email, password, name);
		DBHandler db=new DBHandler();
		db.save(user);
		if(db.getError().length()==0)
		{
			HttpSession session=request.getSession();
			session.setAttribute("user",user);
			session.setMaxInactiveInterval(120);
        RequestDispatcher dispatcher=request.getRequestDispatcher("welcome.jsp");
        dispatcher.forward(request, response);
        }
		else
		{
			RequestDispatcher dispatcher= request.getRequestDispatcher("index.jsp");
			request.setAttribute("error", db.getError());
			dispatcher.forward(request,  response);

		}

		
	}

}
