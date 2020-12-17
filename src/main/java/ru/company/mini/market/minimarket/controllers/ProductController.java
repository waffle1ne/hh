package ru.company.mini.market.minimarket.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.company.mini.market.minimarket.entites.Product;
import ru.company.mini.market.minimarket.exceptions.MarketError;
import ru.company.mini.market.minimarket.exceptions.ResourceNotFoundException;
import ru.company.mini.market.minimarket.service.ProductService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
	private ProductService productService;

	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping
	public ResponseEntity<?> getAllProducts(@RequestParam(required = false) String title) {
		if (title != null) {
			List<Product> productList = productService.getAllProducts().stream()
					.filter(s -> s.getTitle().equals(title))
					.collect(Collectors.toList());
			return !productList.isEmpty() ? new ResponseEntity<>(productList, HttpStatus.OK) : new ResponseEntity<>(new MarketError(HttpStatus.BAD_REQUEST.value(), "Product by title= " + title + " was not found"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public Product getOneById(@PathVariable Long id) {
		return productService.getOneById(id).orElseThrow(() -> new ResourceNotFoundException("Unable to find product with id" + id));
	}

	@PostMapping
	public ResponseEntity<?> createNewProduct(@RequestBody Product p) {
		if (p.getId() != null) {
			return new ResponseEntity<>(new MarketError(HttpStatus.BAD_REQUEST.value(), "New product must have id= null"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(productService.save(p), HttpStatus.CREATED);
	}

	@PatchMapping
	public ResponseEntity<?> modifyProduct(@RequestBody Product p) {
		if (p.getId() == null) {
			return new ResponseEntity<>(new MarketError(HttpStatus.BAD_REQUEST.value(), "Product must have id!= null."), HttpStatus.BAD_REQUEST);
		}
		if (!productService.existsById(p.getId())) {
			return new ResponseEntity<>(new MarketError(HttpStatus.BAD_REQUEST.value(), "Product with id= " + p.getId() + " doesn't exist."), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(productService.save(p), HttpStatus.CREATED);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<?> modifyProductDescription(@PathVariable Long id, @RequestBody String text) {
		Optional<Product> oneById = productService.getOneById(id);
		if (!oneById.isPresent())
			return new ResponseEntity<>(new MarketError(HttpStatus.BAD_REQUEST.value(), "Product for modify not found"), HttpStatus.BAD_REQUEST);
		oneById.get().setDescription(text);
		return new ResponseEntity<>(productService.save(oneById.get()), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		if (!productService.existsById(id)) {
			return new ResponseEntity<>(new MarketError(HttpStatus.BAD_REQUEST.value(), "Product with id= " + id + " doesn't exist."), HttpStatus.BAD_REQUEST);
		}
		productService.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
