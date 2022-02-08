package perscholas.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    // TODO create this controller with these 2 methods to give a view for the login page and for the logoutsuccess page

    @RequestMapping(value = "/login/login", method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request, HttpSession session) throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("login/login");
        return response;
    }

    @RequestMapping(value = "/login/logoutSuccess", method = RequestMethod.GET)
    public ModelAndView logoutSuccess(HttpServletRequest request, HttpSession session) throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("login/logoutSuccess");
        return response;
    }

}
