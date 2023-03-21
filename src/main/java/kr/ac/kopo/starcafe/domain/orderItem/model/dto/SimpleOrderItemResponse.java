package kr.ac.kopo.starcafe.domain.orderItem.model.dto;

import java.io.Serializable;

import kr.ac.kopo.starcafe.domain.orderItem.model.OrderItem;
import kr.ac.kopo.starcafe.domain.product.model.Product;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class SimpleOrderItemResponse implements Serializable {


    private Long id;
    private Product product;
    private int quantity;
    private int countPrice;

    public static SimpleOrderItemResponse of (final OrderItem orderItem) {
        return SimpleOrderItemResponse.builder()
                .id(orderItem.getId())
                .product(orderItem.getProduct())
                .quantity(orderItem.getQuantity())
                .countPrice(orderItem.getCountPrice())
                .build();
    }
}
