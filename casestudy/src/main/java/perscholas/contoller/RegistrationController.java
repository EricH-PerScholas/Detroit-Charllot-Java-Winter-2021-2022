package perscholas.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import perscholas.form.RegisterFormBean;

import javax.validation.Valid;


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
    public ModelAndView registerSubmit(@Valid RegisterFormBean form, BindingResult errors) throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("registration/register");

        System.out.println(form);

        for ( FieldError error : errors.getFieldErrors() ) {
            System.out.println("error field = " + error.getField() + " message = " + error.getDefaultMessage());
        }

        return response;
    }
}
