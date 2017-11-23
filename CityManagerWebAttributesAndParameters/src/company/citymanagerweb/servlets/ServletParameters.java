package company.citymanagerweb.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ServletParameters")
public class ServletParameters extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletParameters() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//in this servlet, we have access to the local
		//servlet init-parameter and the global context-parameter
		String secretCode = 
			getServletConfig().getInitParameter("secretCode");
			
		String dbURL = 
			getServletContext().getInitParameter("dbURL");
				
		//spit out the information
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
				
		String outString = String.format("Secret Code: %s\tDB URL: %s\n"
			, secretCode
			, dbURL);
				
		out.println("<html><body><h3>" + outString + "</h3></body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
