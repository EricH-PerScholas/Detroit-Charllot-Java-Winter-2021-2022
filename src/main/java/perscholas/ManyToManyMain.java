package perscholas;

import java.util.ArrayList;
import java.util.List;

import perscholas.dao.OrderDAO;
import perscholas.dao.ProductDAO;
import perscholas.entity.Order;
import perscholas.entity.Product;

public class ManyToManyMain {
	
	private OrderDAO orderDao = new OrderDAO();
	private ProductDAO productDao = new ProductDAO();

	public void run() {
	
		
		Order order = orderDao.findById(2);
		
		for ( Product product : order.getProductList()) {
			System.out.println(product.getProductName());
		}
		
		Product productToAdd = productDao.findById(4);
		
		System.out.println("Adding product " + productToAdd.getProductName());
		
		// course.getStudents()
		List<Product> products = order.getProductList();
		products.add(productToAdd);
		
		orderDao.save(order);
		
		System.out.println(order);
	}
	
	public void newOrder() {
		Product newProduct = new Product();
		newProduct.setProductName("purple");	
		productDao.save(newProduct);
		
		Order newOrder = new Order();
		newOrder.setShippingType("4 day");
		
		Product tape = productDao.findById(4);
		
		List<Product> products = new ArrayList<Product>();
		products.add(newProduct);
		//products.add(tape);
		newOrder.setProductList(products);
		
		orderDao.save(newOrder);
	}
	
	public static void main(String[] args ) { 
		new ManyToManyMain().newOrder();
	}
}
