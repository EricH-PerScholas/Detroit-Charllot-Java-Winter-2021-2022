package perscholas.contoller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
public class UserController {

    @RequestMapping(value = "/user/profile", method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request, HttpSession session) throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("user/profile");
        return response;
    }

}
