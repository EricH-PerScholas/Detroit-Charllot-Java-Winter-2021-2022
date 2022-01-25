package perscholas.contoller;

import javax.servlet.ServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

	
	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public ModelAndView index() throws Exception {
		ModelAndView response = new ModelAndView();
		response.setViewName("index");

		return response;
	}

	@RequestMapping(value = "/indexSubmit", method = RequestMethod.GET)
	public ModelAndView indexSubmit(ServletRequest request) throws Exception {
		
		// these 3 are the same name as on the html form
		String username = request.getParameter("username");
		String firstName = request.getParameter("firstName");
		String dropdown = request.getParameter("dropdown");

		System.out.println("username = " + username);
		System.out.println("firstName = " + firstName);
		System.out.println("dropdown = " + dropdown);

		
		// this is going to the JSP page
		ModelAndView response = new ModelAndView();
		response.addObject("username", username);
		response.addObject("firstName",firstName);

		response.setViewName("indexSubmit");

		return response;
	}

}
