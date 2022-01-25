package perscholas.contoller;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request, HttpSession session) throws Exception {
		ModelAndView response = new ModelAndView();
		response.setViewName("index");

		// these 3 are the same name as on the html form
		String username = request.getParameter("username");
		String firstName = request.getParameter("firstName");
		String dropdown = request.getParameter("dropdown");

		System.out.println("/index - username = " + username);
		System.out.println("/index - firstName = " + firstName);
		System.out.println("/index - dropdown = " + dropdown);

		// if there is a value in the session print it
		Object userSession = session.getAttribute("username");
		System.out.println("Trying to get user from session and got : " + userSession);

		Cookie[] cookies = request.getCookies();

		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				String name = cookies[i].getName();
				String value = cookies[i].getValue();
				System.out.println("Cookie : " + name + " = " + value);
			}
		} else {
			System.out.println("No cookies on request");
		}
		return response;
	}

	@RequestMapping(value = "/indexSubmit", method = RequestMethod.GET)
	public ModelAndView indexSubmit(HttpServletRequest request, HttpSession session) throws Exception {

		// these 3 are the same name as on the html form
		String username = request.getParameter("username");
		String firstName = request.getParameter("firstName");
		String dropdown = request.getParameter("dropdown");

		System.out.println("/indexSubmit - username = " + username);
		System.out.println("/indexSubmit - firstName = " + firstName);
		System.out.println("/indexSubmit - dropdown = " + dropdown);

		// put a value in the session
		System.out.println("/indexSubmit - adding user to session = " + username);
		session.setAttribute("username", username);

		// this is going to the JSP page
		ModelAndView response = new ModelAndView();
		response.addObject("username", username);
		response.addObject("firstName", firstName);

		response.setViewName("indexSubmit");

		return response;
	}

}
