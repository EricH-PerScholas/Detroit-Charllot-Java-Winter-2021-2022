package perscholas.contoller;

import javax.servlet.ServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	
	// create a login controller
	// create a success.jsp
	// create a login.jsp page with a form that has username and password
	// make the form submit to loginSubmit 
	// in the loginSubmit function check if the username is tom and the password is jerry
	// if true then add tom to the session and return the success view
	// otherwise return the login view
	// in the login method, check if the username tom is in the session and if so return the success view
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(ServletRequest request) throws Exception {
		ModelAndView response = new ModelAndView();
		response.setViewName("login/login");
		
		return response;
	}

}
