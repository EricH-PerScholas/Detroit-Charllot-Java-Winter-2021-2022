package perscholas;

import java.util.List;

import perscholas.dao.UserDAO;
import perscholas.entity.User;

public class Main {

	public static void main(String[] main) {
		UserDAO userDao = new UserDAO();
		
		User user = userDao.findById(1);
		if ( user != null ) {
			System.out.println(user);
		} else {
			System.out.println("Find by user id : user not found");
		}
		
		System.out.println("====================================");
		
		
		List<User> users = userDao.findByFirstNameAndLastName("first", "last");
		for ( User u : users ) {
			System.out.println(u);
		}
		
		System.out.println("====================================");
		
		User newUser = new User();
		newUser.setFirstName("New First");
		newUser.setLastName("New Last");
		newUser.setEmail("new@user.com");
		newUser.setUsername("new username");
		newUser.setPassword("new password");
		
		// save the new user
		newUser = userDao.save(newUser);
		
		System.out.println("!!!!!!!!!!!!!! NEW USER CREATED WITH ID : " + newUser.getId());		
		
		// delete the new user
		//userDao.delete(newUser);
			
		// delete the new user we created by id
		int del = userDao.deleteUserById(newUser.getId());		
		System.out.println("Records Deleted " + del);
		
		System.out.println("==================================== Native Query");
		
		User nat = userDao.findByIdNative(1);
		System.out.println(nat);
		
	}
}
