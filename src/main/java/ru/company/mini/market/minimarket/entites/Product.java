package ru.company.mini.market.minimarket.entites;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="products")
@NoArgsConstructor
@Data
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "title")
	private String title;

	@Column(name = "price")
	private int price;

	@Column(name = "description")
	private String description;
}
