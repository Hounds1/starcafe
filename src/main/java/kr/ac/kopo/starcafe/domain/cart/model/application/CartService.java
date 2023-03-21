package kr.ac.kopo.starcafe.domain.cart.model.application;

import kr.ac.kopo.starcafe.domain.cart.model.Cart;
import kr.ac.kopo.starcafe.domain.cart.model.repository.CartRepository;
import kr.ac.kopo.starcafe.domain.orderItem.model.OrderItem;
import kr.ac.kopo.starcafe.domain.orderItem.model.dto.SimpleOrderItemResponse;
import kr.ac.kopo.starcafe.domain.orderItem.model.repository.OrderItemRepository;
import kr.ac.kopo.starcafe.domain.user.model.User;
import kr.ac.kopo.starcafe.global.security.principal.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CartService {

    private final OrderItemRepository orderItemRepository;
    private final CartRepository cartRepository;

    public void initCart(final Long userId) {
        Cart cart = Cart.builder()
                .userId(userId)
                .build();

        cartRepository.save(cart);
    }

    public SimpleOrderItemResponse addItem(final OrderItem orderItem, final CustomUserDetails details) {
        OrderItem savedOrderItem = orderItemRepository.save(orderItem);

        Cart findCart = cartRepository.findByUserId(details.getId())
                .orElseThrow(() -> new IllegalStateException("장바구니를 불러 올 수 없습니다."));

        findCart.addItem(savedOrderItem);

        return SimpleOrderItemResponse.of(savedOrderItem);
    }

    public void removeItem(final Long id, final CustomUserDetails details) {
        OrderItem findOrderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("주문 아이템을 조회할 수 없습니다."));

        Cart findCart = cartRepository.findByUserId(details.getId())
                .orElseThrow(() -> new IllegalStateException("장바구니를 불러 올 수 없습니다."));

        findCart.removeItem(findOrderItem);

        orderItemRepository.delete(findOrderItem);
    }
}
