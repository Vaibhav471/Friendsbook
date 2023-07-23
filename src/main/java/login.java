

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eschool.dao.DBHandler;
import com.eschool.dao.User;

@WebServlet("/login")
public class login extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		User user=new User(email, password, null);
		DBHandler db=new DBHandler();
		User u=db.isValid(user);
		if(u!=null)
		{
			HttpSession session=request.getSession();
			session.setAttribute("user",u);
			session.setMaxInactiveInterval(60*60);
			
			String checkboxValue = request.getParameter("checkbox");

			if (checkboxValue != null) {

				String combinedValue = email+ ":" +password;
				Cookie cookie = new Cookie("u", combinedValue);

				cookie.setMaxAge(60*5);
				response.addCookie(cookie);

			}
			
        RequestDispatcher dispatcher=request.getRequestDispatcher("welcome.jsp");
        dispatcher.forward(request, response);
        }
		else
		{
			RequestDispatcher dispatcher= request.getRequestDispatcher("index.jsp");
			request.setAttribute("error_login","Invalid id or password");
			dispatcher.forward(request,  response);

		}

		
	}

		
	}


