package perscholas.database.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {

	@Id
	// this annotation is what tells hibernate that this variable is an auto
	// incremented primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "product_name")
	private String productName;

	@Column(name = "image_url")
	private String imageUrl;

}
