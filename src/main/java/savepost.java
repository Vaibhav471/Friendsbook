

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.eschool.dao.DBHandler;
import com.eschool.dao.User;
import com.eschool.dao.Wpost;

@WebServlet("/savepost")
@MultipartConfig(maxFileSize = 16177215)

public class savepost extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		String postcontent=request.getParameter("postcontent");

		InputStream photo=null;

		Part filePart = request.getPart("photo");

		if (filePart != null) 
		{

		photo = filePart.getInputStream();
		}
		

		User user=(User)session.getAttribute("user");
		String sender=user.getEmail();
		Date d=new Date();
		String dop=(d.getYear()+1900)+"-"+(d.getMonth()+1)+"-"+(d.getDate());
		Wpost post=new Wpost(sender,postcontent,dop,photo);
		DBHandler db=new DBHandler();
		db.savePost(post);
		response.sendRedirect("welcome.jsp");
		
		
	}

}
