package kr.ac.kopo.starcafe.domain.orderItem.model.repository;

import kr.ac.kopo.starcafe.domain.orderItem.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
