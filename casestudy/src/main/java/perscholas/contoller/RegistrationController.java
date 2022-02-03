package perscholas.contoller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import perscholas.database.dao.UserDAO;
import perscholas.database.entity.User;
import perscholas.database.form.RegisterFormBean;
import perscholas.dependencyinjectionexample.Worker1;

import javax.validation.Valid;
import java.util.List;


// first thing is to make a controller method that does nothing but return the userList jsp page
// create a simple userList jsp with some basic HTML on ... include your header and footer.

// get a list of users using userDao.findAll()
// add the list of users to the model.  Dont forget to use a key
// on the jsp page create a table and loop over the users to display the properties

// on the jsp page add a search bar inside a form and submit to /userList
// add the search @RequestParam to the controller method
// check to make sure the search parameter is not empty
// if it is not empty change your query to use userDao.findByFirstName(search)


@Controller
@RequestMapping("/registration-url-path")
public class RegistrationController {

    // make sure are import the slf4j object imports for this line of code
    public static final Logger LOG = LoggerFactory.getLogger(RegistrationController.class);


    @Autowired
    private UserDAO userDao;

    // 1) use the existing request mapping to do a firstname OR lastname search case insensitve

    // 2) implement the ability to search by first name AND last name case insensitive - this is a new form on the jsp page
    // I want you to make a new controller request mapping to handle the first name and last name search

    // 3) in both cases I want you to pass the incoming search parameter back to the jsp page using the model
    // I want to populate the search input with the incoming search parameter

    // 4) get your logback config setup and log out stuff to debug

    @RequestMapping(value = "/userList", method = RequestMethod.GET)
    public ModelAndView userList(@RequestParam(required = false) String search, @RequestParam(required = false) String firstName,
                                 @RequestParam(required = false) String lastName) throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("registration/userList");

        if ( ! StringUtils.isEmpty(search)) {
            List<User> users = userDao.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(search,search);
            response.addObject("userListKey", users);
            response.addObject("searchInput", search);
        }

        if ( !StringUtils.isEmpty(firstName) && ! StringUtils.isEmpty(lastName)) {
            List<User> users = userDao.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(firstName,lastName);
            response.addObject("userListKey", users);
        }
        return response;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register() throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("registration/register");

        RegisterFormBean form = new RegisterFormBean();
        response.addObject("formBeanKey", form);

        return response;
    }

    @RequestMapping(value = "/registerSubmit", method = RequestMethod.GET)
    public ModelAndView registerSubmit(@Valid RegisterFormBean form, BindingResult errors) throws Exception {
        ModelAndView response = new ModelAndView();

        System.out.println(form);

        if (errors.hasErrors()) {
            for ( FieldError error : errors.getFieldErrors() ) {
                // add the error message to the errorMessages list in the form bean
                form.getErrorMessages().add(error.getDefaultMessage());
                LOG.debug("error field = " + error.getField() + " message = " + error.getDefaultMessage());
            }

            response.addObject("formBeanKey", form);
            response.setViewName("registration/register");

        } else {
            // there are no errors on the form submission lets redirect to the login page
            // right here that you would save the new user registration to the database
            // however we will get to this later in the week when spring JPA
            User user = new User();

            user.setEmail(form.getEmail());
            user.setFirstName(form.getFirstName());
            user.setLastName(form.getLastName());
            user.setPassword(form.getPassword());
            user.setUsername(form.getUsername());

            userDao.save(user);

            response.setViewName("redirect:/login");
        }

        return response;
    }
}
