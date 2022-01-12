package perscholas.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.query.NativeQuery;

import perscholas.entity.Order;

public class OrderDAO {

	private EntityManager em;

	public OrderDAO() {
		// set this to use the correct persistance unit from persistence.xml
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistanceunit");
		em = emf.createEntityManager();
	}
	
	
	public Order findById(Integer id) {
		try {
			String sql = "SELECT o.* FROM orders o where o.id = :orderId";

			// set this to use the correct entity
			NativeQuery<Order> query = (NativeQuery<Order>) em.createNativeQuery(sql, Order.class);
			query.setParameter("orderId", id);

			Order order = query.getSingleResult();

			return order;
		} catch (Exception e) {
			return null;
		}
	}
	

	

	// passing in the Order that should be saved to the database
	public Order save(Order save) {
		// begin a transaction is required for creating or updating records
		em.getTransaction().begin();
		// tell the entity manager to save this object
		em.persist(save);
		// commit the transaction to actually save the data to the database
		em.getTransaction().commit();

		return save;
	}

	public void delete(Order delete) {
		// begin a transaction is required for creating or updating records
		em.getTransaction().begin();
		// tell the entity manager to delete object
		em.remove(delete);
		// commit the transaction to actually save the data to the database
		em.getTransaction().commit();
	}
	

}
