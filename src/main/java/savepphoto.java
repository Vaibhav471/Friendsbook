

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.eschool.dao.DBHandler;
import com.eschool.dao.Pphoto;
import com.eschool.dao.User;
import com.eschool.dao.Wpost;

@WebServlet("/savepphoto")
@MultipartConfig(maxFileSize = 16177215)

public class savepphoto extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=request.getSession();

		InputStream photo=null;

		Part filePart = request.getPart("pic");

		if (filePart != null) 
		{

		photo = filePart.getInputStream();
		}
		

		User user=(User)session.getAttribute("user");
		String sender=user.getEmail();
		Date d=new Date();
		String doup=(d.getYear()+1900)+"-"+(d.getMonth()+1)+"-"+(d.getDate());
		Pphoto p=new Pphoto(sender,photo,doup);
		DBHandler db=new DBHandler();
		db.savePphoto(p);
		String po=user.getEmail();
		System.out.print(po);
		request.setAttribute("po",po);
		RequestDispatcher dispatcher=request.getRequestDispatcher("/profile.jsp");
		dispatcher.forward(request, response);
		
			
			
		
	}

}
