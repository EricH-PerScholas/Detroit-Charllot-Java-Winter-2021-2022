package perscholas.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import perscholas.database.dao.ProductDAO;
import perscholas.database.entity.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ProductContoller {

    @Autowired
    private ProductDAO productDao;

    @RequestMapping(value = "/product/list", method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request, HttpSession session) throws Exception {
        ModelAndView response = new ModelAndView();

        List<Product> products = productDao.findAll();
        response.addObject("products", products);

        response.setViewName("product/productList");
        return response;
    }

}
