package perscholas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.query.NativeQuery;

import perscholas.entity.User;

public class UserDAO {

	private EntityManager em;

	public UserDAO() {
		// set this to use the correct persistance unit from persistence.xml
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistanceunit");
		em = emf.createEntityManager();
	}
	
	
	public User findByIdNative(Integer id) {
		try {
			String sql = "SELECT u.* FROM users u where u.id = :userid";

			// set this to use the correct entity
			NativeQuery<User> query = (NativeQuery<User>) em.createNativeQuery(sql, User.class);
			query.setParameter("userid", id);

			User user = query.getSingleResult();

			return user;
		} catch (Exception e) {
			return null;
		}
	}
	

	public User findById(Integer id) {
		try {
			String sql = "SELECT u FROM User u where id = :userid";

			// set this to use the correct entity
			TypedQuery<User> query = em.createQuery(sql, User.class);
			query.setParameter("userid", id);

			User user = query.getSingleResult();

			return user;
		} catch (Exception e) {
			return null;
		}
	}
	
	public User findByEmail(String email) {
		try {
			String sql = "SELECT u FROM User u where email = :email";

			// set this to use the correct entity
			TypedQuery<User> query = em.createQuery(sql, User.class);
			query.setParameter("email", email);

			User user = query.getSingleResult();

			return user;
		} catch (Exception e) {
			return null;
		}
	}

	public List<User> findByFirstNameAndLastName(String firstName, String lastName) {
		try {
			String sql = "SELECT u FROM User u where firstName = :firstName and lastName = :lastName";

			// set this to use the correct entity
			TypedQuery<User> query = em.createQuery(sql, User.class);
			query.setParameter("firstName", firstName);
			query.setParameter("lastName", lastName);

			List<User> user = query.getResultList();

			return user;
		} catch (Exception e) {
			return null;
		}
	}

	// passing in the user that should be saved to the database
	public User save(User save) {
		// begin a transaction is required for creating or updating records
		em.getTransaction().begin();
		// tell the entity manager to save this object
		em.persist(save);
		// commit the transaction to actually save the data to the database
		em.getTransaction().commit();

		return save;
	}

	public void delete(User delete) {
		// begin a transaction is required for creating or updating records
		em.getTransaction().begin();
		// tell the entity manager to delete object
		em.remove(delete);
		// commit the transaction to actually save the data to the database
		em.getTransaction().commit();
	}
	
	public int deleteUserById(Integer id) {
		String sql = "DELETE FROM User where id = :userid";

		// set this to use the correct entity
		Query query = em.createQuery(sql);
		query.setParameter("userid", id);

		em.getTransaction().begin();
		int recordsUpdated = query.executeUpdate();
		em.getTransaction().commit();

		return recordsUpdated;
	}
}
