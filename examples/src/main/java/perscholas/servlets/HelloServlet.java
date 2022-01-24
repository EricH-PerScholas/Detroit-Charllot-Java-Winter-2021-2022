package perscholas.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet {

	private String message;

	public void init() throws ServletException {
		// Do required initialization
		message = "Hello World";
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Set response content type
		response.setContentType("text/html");

		// Actual logic goes here.
		PrintWriter out = response.getWriter();
		out.println("<h1>" + message + "</h1>");
		out.println("<h2>h2 header</h2>");
		
		String id = request.getParameter("id");
		out.println("<p>id = " + id + "</p>");
		
		String name = request.getParameter("name");
		out.println("<p>name = " + name + "</p>");	
		
		Enumeration<String> names = request.getParameterNames();
		while ( names.hasMoreElements() ) {
			String key = names.nextElement();
			String value = request.getParameter(key);
			
			out.println("<p><b>key = " + key + " value = " + value + "</b></p>");
		}
		
		String encoding = request.getCharacterEncoding();
		out.println("<h2>" + encoding + "</h2>");

	}

	public void destroy() {
		// do nothing.
	}
}
