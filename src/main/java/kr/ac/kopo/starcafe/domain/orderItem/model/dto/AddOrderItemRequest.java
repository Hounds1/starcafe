package kr.ac.kopo.starcafe.domain.orderItem.model.dto;

import kr.ac.kopo.starcafe.domain.orderItem.model.OrderItem;
import kr.ac.kopo.starcafe.domain.product.model.Product;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class AddOrderItemRequest {

    private Product product;

    private int quantity;

    public OrderItem toEntity() {
        int countPrice = product.getPrice() * quantity;

        return OrderItem.builder()
                .product(product)
                .quantity(quantity)
                .countPrice(countPrice)
                .build();
    }
}
