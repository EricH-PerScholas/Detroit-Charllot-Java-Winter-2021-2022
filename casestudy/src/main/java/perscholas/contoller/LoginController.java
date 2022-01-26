package perscholas.contoller;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

	// create a login controller
	// create a login.jsp page with a form that has username and password
	// create a success.jsp
	// make the form submit to loginSubmit ( create new method on controller )
	// in the loginSubmit function check if the username is tom and the password is
	// jerry
	// if true then add tom to the session and return the success view
	// otherwise return the login view
	// in the login method, check if the username tom is in the session and if so return the success view
	// do not worry about using post - use get on the form.

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request, HttpSession session) throws Exception {
		// this method is checking to see if the user is logged in by looking at the session
		// if logged in ( user is in the session ) then show the success page.
		// if not logged in ( user is not in the session ) then show the login page
		ModelAndView response = new ModelAndView();

		String username = (String) session.getAttribute("usernameSessionKey");
		if (StringUtils.equals(username, "tom")) {
			response.setViewName("login/success");
			response.addObject("loggedInUser", username);
		} else {
			response.setViewName("login/login");
		}

		return response;
	}

	@RequestMapping(value = "/loginFormSubmit", method = RequestMethod.GET)
	public ModelAndView loginSubit(HttpServletRequest request, HttpSession session) throws Exception {
		ModelAndView response = new ModelAndView();

		String username = request.getParameter("usernameFromForm");
		String password = request.getParameter("passwordFromForm");

		// if ("tom".equals(username) && "jerry".equals(password) ){
		if (StringUtils.equals(username, "tom") && StringUtils.equals(password, "jerry")) {
			session.setAttribute("usernameSessionKey", username);
			response.addObject("loggedInUser", username);
			response.setViewName("login/success");
		} else {
			// invalid login
			session.setAttribute("username", null);
			response.setViewName("login/login");
		}

		return response;
	}

}