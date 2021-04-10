import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/SaveServlet")
public class SaveServlet extends HttpServlet {
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException
	{
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		String name = req.getParameter("name");
		String pass = req.getParameter("password");
		String email = req.getParameter("email");
		String country = req.getParameter("country");
		
		Emp e = new Emp();
		e.setName(name);
		e.setPassword(pass);
		e.setEmail(email);
		e.setCountry(country);
		
		int status = EmpDao.save(e);
		if(status > 0)
		{
			out.println("<h1> Record Saved Successfully </h1>");
			req.getRequestDispatcher("index.html").include(req, res);  
		}
		else{  
            out.println("Sorry! unable to save record");  
            req.getRequestDispatcher("index.html").include(req, res);
        }  
	}
}
