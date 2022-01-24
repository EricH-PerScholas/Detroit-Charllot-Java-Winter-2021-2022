package perscholas.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.query.NativeQuery;

import perscholas.entity.Product;

public class ProductDAO {

	private EntityManager em;

	public ProductDAO() {
		// set this to use the correct persistance unit from persistence.xml
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistanceunit");
		em = emf.createEntityManager();
	}
	
	
	public Product findById(Integer id) {
		try {
			String sql = "SELECT * FROM products where id = :id";

			// set this to use the correct entity
			NativeQuery<Product> query = (NativeQuery<Product>) em.createNativeQuery(sql, Product.class);
			query.setParameter("id", id);

			Product order = query.getSingleResult();

			return order;
		} catch (Exception e) {
			return null;
		}
	}
	

	

	// passing in the Product that should be saved to the database
	public Product save(Product save) {
		// begin a transaction is required for creating or updating records
		em.getTransaction().begin();
		// tell the entity manager to save this object
		em.persist(save);
		// commit the transaction to actually save the data to the database
		em.getTransaction().commit();

		return save;
	}

	public void delete(Product delete) {
		// begin a transaction is required for creating or updating records
		em.getTransaction().begin();
		// tell the entity manager to delete object
		em.remove(delete);
		// commit the transaction to actually save the data to the database
		em.getTransaction().commit();
	}
	

}
