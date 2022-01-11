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
	
	@Test
	public void addAndRemove() {
		User newUser = new User();
		newUser.setFirstName("New First");
		newUser.setLastName("New Last");
		newUser.setEmail("new@user.com");
		newUser.setUsername("new username");
		newUser.setPassword("new password");
		
		// save the new user
		newUser = userDao.save(newUser);
		
		User created = userDao.findByEmail("new@user.com");
		
		// first test to make sure the user was created in the database
		Assert.assertNotNull(created);
		Assert.assertEquals(created, "new@user.com");
		
		// delete the user we just created
		userDao.delete(created);
		
		// requery the database to see that user is gone
		User deleted = userDao.findByEmail("new@user.com");
		
		Assert.assertNull(deleted);
			
		
	}
}
