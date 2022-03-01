package perscholas.database.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.awt.print.Book;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {

	@Id
	// this annotation is what tells hibernate that this variable is an auto
	// incremented primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "order_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date orderDate;

	@Column(name = "shipping_type")
	private String shippingType;

//	@ManyToOne(fetch = FetchType.LAZY, optional = false)
//	@JoinColumn(name = "user_id", nullable = false)
//	private User user;
//
//	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
//	@JoinTable(name = "order_product",
//			joinColumns = {
//					@JoinColumn(name = "product_id", referencedColumnName = "id",
//							nullable = false, updatable = false)},
//			inverseJoinColumns = {
//					@JoinColumn(name = "order_id", referencedColumnName = "id",
//							nullable = false, updatable = false)})
//	private Set<Product> products = new HashSet<>();

}
