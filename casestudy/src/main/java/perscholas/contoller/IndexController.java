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
		
		System.out.println("username = " + request.getParameter("username") );	
		System.out.println("firstName = " + request.getParameter("firstName") );		
		
		
		ModelAndView response = new ModelAndView();
		response.setViewName("index");

		return response;
	}
	
}
