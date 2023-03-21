package kr.ac.kopo.starcafe.domain.cart.model.presentaion;

import kr.ac.kopo.starcafe.domain.cart.model.application.CartReadService;
import kr.ac.kopo.starcafe.domain.cart.model.application.CartService;
import kr.ac.kopo.starcafe.domain.orderItem.model.OrderItem;
import kr.ac.kopo.starcafe.domain.orderItem.model.dto.AddOrderItemRequest;
import kr.ac.kopo.starcafe.domain.orderItem.model.dto.SimpleOrderItemResponse;
import kr.ac.kopo.starcafe.global.security.principal.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CartController {

    private final CartService cartService;
    private final CartReadService cartReadService;

    @PostMapping("/api/carts")
    public ResponseEntity<SimpleOrderItemResponse> addItem(@RequestBody final AddOrderItemRequest request) {
        return ResponseEntity.ok().body(cartService.addItem(request.toEntity(), getPrincipal()));
    }

    @DeleteMapping("/api/carts")
    public ResponseEntity<Void> removeItem(@RequestBody final Long id) {
        cartService.removeItem(id, getPrincipal());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/carts/all")
    public ResponseEntity<List<OrderItem>> getAllItem() {
        return ResponseEntity.ok().body(cartReadService.getAllItem(getPrincipal()));
    }

    public CustomUserDetails getPrincipal() {
        return (CustomUserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
    }
}
