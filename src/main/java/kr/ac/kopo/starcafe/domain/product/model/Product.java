package kr.ac.kopo.starcafe.domain.product.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kr.ac.kopo.starcafe.domain.product.model.dto.Category;
import kr.ac.kopo.starcafe.domain.product.model.dto.ProductModifiedRequest;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "product_name")
    private String name;

    @Column(name = "product_price")
    private int price;

    @Enumerated(EnumType.STRING)
    private Category category;

    /**
     * 비즈니스 로직
     */

    public void modified (ProductModifiedRequest req) {
        this.name = req.getName();
        if (category != req.getCategory()) {
            this.category = req.getCategory();
        }
    }
}
