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

		return response;
	}



}
