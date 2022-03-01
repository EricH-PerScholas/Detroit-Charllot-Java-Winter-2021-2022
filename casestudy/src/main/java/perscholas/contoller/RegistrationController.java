package perscholas.contoller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import perscholas.database.dao.UserDAO;
import perscholas.database.dao.UserRoleDAO;
import perscholas.database.entity.User;
import perscholas.database.entity.UserRole;
import perscholas.form.RegisterFormBean;

import javax.validation.Valid;
import java.util.ArrayList;
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
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDAO userDao;

    @Autowired
    private UserRoleDAO userRoleDao;

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

    // this method is responsible for populating the jsp page with the correct data so the page can render
    // if this method is called without the id parameter it will be a create and no database will be loaded
    // if it is called with an id it will be an edit and we need to load the user from the databse and
    // populate the form bean.
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register() throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("registration/register");

        return response;
    }


    public ModelAndView registerEditPage(@RequestParam(required = false) Integer id ) {
        ModelAndView response = new ModelAndView();
        response.setViewName("registration/register");

        if ( id != null ) {
            // id has been passed to this form so it is an edit
            User user = userDao.findById(id);

            // populate the form bean with the data loaded from the database
            RegisterFormBean form = new RegisterFormBean();
            form.setEmail(user.getEmail());
            form.setFirstName(user.getFirstName());
            form.setLastName(user.getLastName());
            form.setUsername(user.getUsername());
            form.setPassword(user.getPassword());
            form.setConfirmPassword(user.getPassword());
            // since we loaded this from the database we know the id field
            form.setId(user.getId());

            response.addObject("formBeanKey", form);
        } else {
            // an id has not been passed so it is a create
            // there is no data from the database so give an empty form bean
            RegisterFormBean form = new RegisterFormBean();
            response.addObject("formBeanKey", form);
        }

        return response;
    }



    // this method describes what happens when a user submits the form to the back end
    // it handles both the create and update logic for saving the user input to the database
    @RequestMapping(value = "/registerSubmit", method = { RequestMethod.POST, RequestMethod.GET })
    public ModelAndView registerSubmit(@Valid RegisterFormBean form, BindingResult errors) throws Exception {
        ModelAndView response = new ModelAndView();

        System.out.println(form);

        if (errors.hasErrors()) {
            // this list is populated by the controller with all error messages
            // in the binding result.
            List<String> errorMessages = new ArrayList<>();

            for ( FieldError error : errors.getFieldErrors() ) {
                // add the error message to the errorMessages list in the form bean
                errorMessages.add(error.getDefaultMessage());
                LOG.debug("error field = " + error.getField() + " message = " + error.getDefaultMessage());
            }

            response.addObject("errorMessages", errorMessages);
            response.addObject("formBeanKey", form);
            response.setViewName("registration/register");

        } else {
            // there are no errors on the form submission so this is either a create or an update.

            // no matter what we need to create a new user object
            User user;

            if ( form.getId() == null ) {
                // the id is not present in the form bean so we know this is a create
                user  = new User();
            } else {
                // this is an update so we need to load the user from the database first
                user = userDao.findById(form.getId());
            }

            user.setEmail(form.getEmail());
            user.setFirstName(form.getFirstName());
            user.setLastName(form.getLastName());
            user.setUsername(form.getUsername());

            // use this line of code if you wanted to manually check a password against a hash
            // in this case we would be comparing a password entered on a form with the current password on the user record.
            boolean matches = passwordEncoder.matches(form.getPassword(), user.getPassword());

            // use these 2 lines to setup the encoded password for this user in the database
            String encryptedPassword = passwordEncoder.encode(form.getPassword());
            user.setPassword(encryptedPassword);

            // if you are saving a new user without an id.  The return value of save will
            // create a new auto incremented ID record and return the user object with the new id populated
            user = userDao.save(user);

            if ( form.getId() == null ) {
                // this is a create because the incoming id variable on the form is null
                // so ... lets create a user role record for this user also
                UserRole ur = new UserRole();

                ur.setUser(user);
                ur.setUserRole("USER");

                userRoleDao.save(ur);
            }

            // check if this is an edit vs a create and redirect accordingly
            // redirect to login page is best thing to do
           response.setViewName("redirect:/login/login");
           // response.setViewName("registration/register");
        }

        return response;
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
    public ModelAndView delete(@RequestParam Integer id ) throws Exception {
        ModelAndView response = new ModelAndView();

        response.setViewName("redirect:/registration-url-path/userList");

        User delete = userDao.findById(id);
        if ( delete != null ) {
            userDao.delete(delete);
        }

        return response;
    }

    public User getLoggedInUser() {
        // this is boiler plate code to get the authentication information from spring security
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // gets the username that the user logged in with
        String currentPrincipalName = authentication.getName();
        // query the database to get the user object based on the logged in username
        // in the case that you have used the email address to get the username
        return userDao.findByEmail(currentPrincipalName);
        // or you would use this line to get the user by username if you used username to login
        // return userDao.findByUsername(currentPrincipalName);
    }

    @RequestMapping(value = "/addToCart", method = RequestMethod.GET)
    public void addToCart(@RequestParam(required = true) Integer productId ) {
        // for adding an item to an order
        // 0) on your jsp page when a user adds an item to the cart you will pass the product id
        // 1) query your product by the productId
        // 1.1)  get the user record for the logged-in user with getLoggedInUser function
        // 2) query your oder by the user_id and status cart ( this gets the most recent order for the logged-in user)
        // 3) if the order does not exist ( id of the query response is null )
        //      3a) create the order with status cart
        //      3c) add the user object to the order
        // 4) query the order_products table to see if the product is already in the order
        // 5) if the product is not in the order
        //      5a ) create a new order_product entity
        //      5b ) set the product id on the order_product
        //      5c ) set the order id on the order_product
        // 6) if the product is already in the order
        //      6a ) increment the quantity on the order_product
        // 7) persist the order_product

    }
}
