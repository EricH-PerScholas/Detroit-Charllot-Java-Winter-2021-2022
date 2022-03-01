package perscholas.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


import perscholas.database.dao.UserDAO;
import perscholas.database.dao.UserRoleDAO;
import perscholas.database.entity.User;
import perscholas.database.entity.UserRole;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

	public static final Logger LOG = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

	@Autowired
	private UserDAO userDao;

	@Autowired
	private UserRoleDAO userRoleDao;

	@Override
	// ####################### the variable name for this method does not matter !!!!! #######################
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// if the incoming username or email is out found in the database
		// this user will be null
		// TODO if you want to login using username column you can change this to findByUsername or even findByUsernameOrEmail
		User user = userDao.findByEmail(email);
		//User user = userDao.findByUsername(username);

		if (user == null) {
			// this means that the username was not found in the database so we are done
			// and we can get out of here
			throw new UsernameNotFoundException("Username '" + email + "' not found in database");
		}

		// we got here so it means the user was found in the database
		List<UserRole> userRoles = userRoleDao.getUserRoles(user.getId());

		Collection<? extends GrantedAuthority> springRoles = buildGrantAuthorities(userRoles);
		return new org.springframework.security.core.userdetails.User(email, user.getPassword(), springRoles);
	}

	private Collection<? extends GrantedAuthority> buildGrantAuthorities(List<UserRole> userRoles) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		for (UserRole role : userRoles) {
			authorities.add(new SimpleGrantedAuthority(role.getUserRole().toString()));
		}

		return authorities;
	}

	/**
	CREATE TABLE `user_roles` (
			`id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
			`user_role` varchar(45) COLLATE utf8mb4_bin NOT NULL,
	PRIMARY KEY (`id`),
	UNIQUE KEY `ur_unique` (`user_role`,`user_id`) ,
	KEY `FK_user_id_idx` (`user_id`),
	CONSTRAINT `FK_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
			) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin
	*/

}
