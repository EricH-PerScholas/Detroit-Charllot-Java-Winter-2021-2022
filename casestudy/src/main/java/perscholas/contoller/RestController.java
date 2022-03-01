package perscholas.contoller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import perscholas.database.dao.UserDAO;
import perscholas.database.dao.UserRoleDAO;
import perscholas.database.entity.User;
import perscholas.database.entity.UserRole;
import perscholas.form.RegisterFormBean;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.List;

//@org.springframework.web.bind.annotation.RestController
@Controller
//@ResponseBody
@RequestMapping("/rest")
public class RestController {

    // assignment
    //
    // install json formatter to chrome browser
    // create a rest controller
    // implement a get mapping using a path variable
    // implement a get mapping that returns a list


    @Autowired
    private UserDAO userDao;

    @Autowired
    private UserRoleDAO userRoleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping(value ="/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getList(@RequestParam String firstName) {
        List<User> users = userDao.findByFirstNameLike(firstName);
        return users;
    }

    // in a rest controller a get method is always used for reads
    @RequestMapping(value ="/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public User get(@PathVariable Integer id) {
        User user = userDao.findById(id);
        return user;
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> delete(@PathVariable Integer id) {
        User user = userDao.findById(id);

        if ( id != null && user != null ) {
            List<UserRole> roles = userRoleDao.getUserRoles(id);

            for ( UserRole role : roles ) {
                userRoleDao.delete(role);
            }

            userDao.delete(user);

            //JSONObject jo = new JSONObject();
            //jo.put("status", "success");
            //jo.put("response_code", "200");
            return new ResponseEntity<Long>(id.longValue(), HttpStatus.OK);
        } else {
            return new ResponseEntity<Long>( HttpStatus.NOT_FOUND);
        }
    }

    // this method creates a new user
    @RequestMapping(value ="/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> get(@Valid RegisterFormBean form, BindingResult errors) {
        User user = new User();

        user.setEmail(form.getEmail());
        user.setFirstName(form.getFirstName());
        user.setLastName(form.getLastName());
        user.setUsername(form.getUsername());

        String encryptedPassword = passwordEncoder.encode(form.getPassword());
        user.setPassword(encryptedPassword);

        userDao.save(user);

        return new ResponseEntity<Long>(HttpStatus.OK);
    }

    // this method creates a new user
    @RequestMapping(value ="", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> put(@Valid RegisterFormBean form, BindingResult errors) {
        User user = userDao.findById(form.getId());

        user.setEmail(form.getEmail());
        user.setFirstName(form.getFirstName());
        user.setLastName(form.getLastName());
        user.setUsername(form.getUsername());

        String encryptedPassword = passwordEncoder.encode(form.getPassword());
        user.setPassword(encryptedPassword);

        userDao.save(user);

        return new ResponseEntity<Long>(HttpStatus.OK);
    }
}
