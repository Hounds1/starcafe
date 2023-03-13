package kr.ac.kopo.starcafe.domain.product.repository;

import kr.ac.kopo.starcafe.domain.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MockProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(final String name);
}
