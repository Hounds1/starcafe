package kr.ac.kopo.starcafe.domain.cart.model;

import kr.ac.kopo.starcafe.domain.orderItem.model.OrderItem;
import kr.ac.kopo.starcafe.domain.user.model.User;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Cart {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_item_id")
    List<OrderItem> itemList = new ArrayList<>();


    /**
     * 비즈니스 로직
     */

    public void addItem(OrderItem orderItem) {
        this.itemList.add(orderItem);
    }

    public void removeItem(OrderItem orderItem) {
        int index = this.itemList.indexOf(orderItem);
        if(index != -1) {
            this.itemList.remove(index);
        }
    }
}
