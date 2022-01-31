package perscholas.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/registration-url-path")
public class RegistrationController {

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register() throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("registration/register");

        return response;
    }

    @RequestMapping(value = "/registerSubmit", method = RequestMethod.GET)
    public ModelAndView registerSubmit() throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("registration/register");

        return response;
    }
}
