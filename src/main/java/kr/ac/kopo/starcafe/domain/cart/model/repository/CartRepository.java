package kr.ac.kopo.starcafe.domain.cart.model.repository;

import kr.ac.kopo.starcafe.domain.cart.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUserId(final Long id);
}
