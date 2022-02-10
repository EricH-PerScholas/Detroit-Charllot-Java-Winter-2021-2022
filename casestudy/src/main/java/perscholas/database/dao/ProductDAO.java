package perscholas.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import perscholas.database.entity.Product;
import perscholas.database.entity.UserRole;


public interface ProductDAO extends JpaRepository<Product, Long> {

}