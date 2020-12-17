package ru.company.mini.market.minimarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.company.mini.market.minimarket.entites.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
