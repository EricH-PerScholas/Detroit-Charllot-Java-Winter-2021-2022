package perscholas.dao;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import perscholas.entity.User;

public class UserDAOTest {
	
	// before all method needs to be static so this variable needs to be static
	private static UserDAO userDao;
	
	@BeforeAll
	public static void beforeAll() {
		// junit requires this method has to be static to run
		// create a new instance of your service clas to be tested
		System.out.println("Before All Method");
		
		userDao = new UserDAO();
	}
	

	@Test
	public void findUserByIdTest() {
		System.out.println("IIN TEST ");
		
		User user = userDao.findById(1);
		
		Assert.assertEquals(user.getUsername(), "username");
		Assert.assertEquals(user.getFirstName(), "first");
	}
}
