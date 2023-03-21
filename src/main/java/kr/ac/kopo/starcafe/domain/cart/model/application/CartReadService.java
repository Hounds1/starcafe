package kr.ac.kopo.starcafe.domain.cart.model.application;

import kr.ac.kopo.starcafe.domain.cart.model.Cart;
import kr.ac.kopo.starcafe.domain.cart.model.repository.CartRepository;
import kr.ac.kopo.starcafe.domain.orderItem.model.OrderItem;
import kr.ac.kopo.starcafe.global.security.principal.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class CartReadService {

    private final CartRepository cartRepository;

    public List<OrderItem> getAllItem(final CustomUserDetails details) {
        Cart findCart = cartRepository.findByUserId(details.getId())
                .orElseThrow(() -> new IllegalStateException("장바구니를 불러올 수 없습니다."));

        return findCart.getItemList();
    }
}
