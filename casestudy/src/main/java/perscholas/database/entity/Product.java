package perscholas.database.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "image_url")
	private String imageUrl;

	@Column(name = "description")
	private String description;

	@Column(name = "category")
	private String category;

	@Column(name = "price")
	private Double price;


}
