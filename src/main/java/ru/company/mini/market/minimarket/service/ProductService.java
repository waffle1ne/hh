package ru.company.mini.market.minimarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.company.mini.market.minimarket.entites.Product;
import ru.company.mini.market.minimarket.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
	private ProductRepository productRepository;

	@Autowired
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	public Optional<Product> getOneById(Long id) {
		return productRepository.findById(id);
	}

	public Product save(Product product) {
		return productRepository.save(product);
	}

	public boolean existsById(Long id) {
		return productRepository.existsById(id);
	}

	public void deleteById(Long id) {
		productRepository.deleteById(id);
	}
}
