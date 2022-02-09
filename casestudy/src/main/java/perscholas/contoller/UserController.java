package perscholas.contoller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import perscholas.database.dao.UserDAO;
import perscholas.database.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;

@Slf4j
@Controller
@PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
public class UserController {

    @Autowired
    private UserDAO userDao;

    @RequestMapping(value = "/user/profile", method = RequestMethod.GET)
    public ModelAndView index() throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("user/profile");

        // do your queries to get whatever you want to display on this page
        // there is a way to ask the security context for the logged in user.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        User user = userDao.findByEmail(currentPrincipalName);
        response.addObject("user", user);
        // add the user to the model

        return response;
    }

    // -------------------- commons file upload methods ---------------------------

    @RequestMapping(value = "/user/fileUpload", method = RequestMethod.GET)
    public ModelAndView upload() throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("user/fileUpload");
        return response;
    }

    @RequestMapping(value = "/user/fileUploadSubmit", method = RequestMethod.POST)
    public ModelAndView uploadSubmit(@RequestParam MultipartFile file, @RequestParam(required = false) String title) throws Exception {
        // figure out what the default OS temp directory is
        String tempdir = System.getProperty("java.io.tmpdir") + File.separator + "perscholas";

        // create a new file object for this directory and see if the directory exists
        File saveFileDirectory = new File(tempdir);
        if (! saveFileDirectory.exists() ) {
            // it does not exist so make the temp directory using mkdirs
            // mkdirs will create any parent folders needed
            log.debug("Creating temp folder for file upload : " + saveFileDirectory.getAbsolutePath());
            saveFileDirectory.mkdirs();
        }

        // build the full path to file we want to save
        String saveFileName = saveFileDirectory.getAbsolutePath() + File.separator + file.getOriginalFilename();
        File targetFile = new File(saveFileName);

        if ( targetFile.exists() ) {
            throw new Exception("Unable to save uploaded file " + file.getOriginalFilename() + " because a file with that name already exists");
        }
        // save the uploaded file to the hard drive using commons io
        // this will take the uploaded file stream and write it to the target file on the disk
        FileUtils.copyInputStreamToFile(file.getInputStream(), targetFile);

        log.debug("Uploaded file saved to : " + targetFile.getAbsolutePath());

        ModelAndView response = new ModelAndView();
        response.setViewName("user/fileUpload");
        return response;
    }

}
