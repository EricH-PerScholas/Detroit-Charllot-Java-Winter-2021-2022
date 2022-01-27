package perscholas.contoller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;
import perscholas.form.LoginFormBean;

@Controller
//@RequestMapping("/cart")
public class LoginController {

	// exercise 1
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

	// the goal is to have the correct URL showing for the correct page
	// the form submission is invisible to the user, as in it will not show in the browser url

	// do these test cases in the debugger
	// test 1
	// goto /login when the user has not been logged in
	// login and submit 
	// this will execute through the loginSubit method and the success method
	// make sure you are on the /success page

	// test 2
	// click logout and make sure you goto the /login page 
	// and /login is in the url

	// test 3
	// goto /success and make sure that you are redirected to the /login page

	// test 4
	// login with failed credentials and make sure you are on the /login page again

	// test 5
	// login with tom and jerry which will be on the success page and /success in the url
	// type /login on the url and make sure you are redirected to /success and show the success page

	// exercise 2
	// use setAttribute to set an error message in the session.   In the formSubmit2 method
	// getAttribute to get the error message from the session in the login method.
	// response (model) .addObject to make the error message available to the JSP
	// alter the login.jsp to show the error message from the model using ${ } notation

	private static String SESSION_KEY = "usernameSessionKey";
	private static String SESSION_ERROR_MESSAGE = "errorMessageKey";

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request, HttpSession session) throws Exception {
		// this method is checking to see if the user is logged in by looking at the session
		// if logged in ( user is in the session ) then show the success page.
		// if not logged in ( user is not in the session ) then show the login page
		ModelAndView response = new ModelAndView();

		String username = (String) session.getAttribute(SESSION_KEY);
		if (StringUtils.equals(username, "tom")) {
			// when using redirect you will use the URL of the controller method 
			// that you want to display.  In this case the /success RequestMapping
			response.setViewName("redirect:http://localhost:8080/success");

		} else {
			// when using the name of a view we use the path to the JSP page
			// within the jsp folder.
			String errorMessage = (String)session.getAttribute(SESSION_ERROR_MESSAGE);
			response.addObject("errorMessage", errorMessage);
			response.setViewName("login/login");
		}

		return response;
	}

	// using the @RequestParam in the method signature is a better way of getting form variables
	// that using request.getParameter
	@RequestMapping(value = "/loginFormSubmit", method = RequestMethod.GET)
	public ModelAndView loginSubmit(LoginFormBean form, @RequestParam String passwordFromForm, @RequestParam String usernameFromForm,
								   HttpServletRequest request, HttpSession session  ) throws Exception {
		ModelAndView response = new ModelAndView();

		System.out.println("usernameFromFrom using @RequestParam = " + usernameFromForm);
		System.out.println("usernameFromFrom using LoginFormBean = " + form.getUsernameFromForm());
		String username = request.getParameter("usernameFromForm");
		String password = request.getParameter("passwordFromForm");

		// if ("tom".equals(username) && "jerry".equals(password) ){
		if (StringUtils.equals(username, "tom") && StringUtils.equals(password, "jerry")) {
			session.setAttribute(SESSION_KEY, username);
			response.setViewName("redirect:/success");
		} else {
			// invalid login
			// setting session to null to ensure the user is logged out
			session.setAttribute(SESSION_KEY, null);
			response.setViewName("redirect:/login");
		}

		return response;
	}

	@RequestMapping(value = "/loginFormSubmit2", method = RequestMethod.GET)
	public ModelAndView loginFormSubmit(LoginFormBean form, HttpSession session  ) throws Exception {
		ModelAndView response = new ModelAndView();

		String username = form.getUsernameFromForm();
		String password = form.getPasswordFromForm();

		if (StringUtils.equals(username, "tom") && StringUtils.equals(password, "jerry")) {
			session.setAttribute(SESSION_KEY, form.getUsernameFromForm());
			response.setViewName("redirect:/success");
			session.setAttribute(SESSION_ERROR_MESSAGE, null);
		} else {
			// invalid login
			// setting session to null to ensure the user is logged out
			session.setAttribute(SESSION_KEY, null);
			response.setViewName("redirect:/login");

			session.setAttribute(SESSION_ERROR_MESSAGE, "Invalid Login");
		}

		return response;
	}

	@RequestMapping(value = "/success", method = RequestMethod.GET)
	public ModelAndView success(HttpSession session) throws Exception {
		// this method is checking to see if the user is logged in by looking at the session
		// if logged in ( user is in the session ) then show the success page.
		// if not logged in ( user is not in the session ) then show the login page
		ModelAndView response = new ModelAndView();

		String username = (String) session.getAttribute(SESSION_KEY);
		if (StringUtils.equals(username, "tom")) {
			// add the username to the response model so that it can be displayed on the jsp page.
			response.addObject("loggedInUser", username);

			response.setViewName("login/success");
		} else {
			// need to implement here to redirect back to login page
			// because it means the user has requested the /success url
			// but is not in the session
			response.setViewName("redirect:/login");
		}

		return response;
	}


	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpSession session) throws Exception {
		// this is how to destroy the current user session
		// always implement a logout method this way.
		session.invalidate();
		ModelAndView response = new ModelAndView();
		// this is how to do a redirect in spring mvc usin the model
		// this will change the url to be localhost:8080/login
		// which is preferable because the URL is the same as the page you are on
		response.setViewName("redirect:/login");

		// doing it this way will work but is not best practice
		//response.setViewName("login/login");

		return response;
	}

}
