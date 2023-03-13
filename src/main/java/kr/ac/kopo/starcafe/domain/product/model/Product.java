package kr.ac.kopo.starcafe.domain.product.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kr.ac.kopo.starcafe.domain.category.model.Category;
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    @JsonIgnore
    private Category category;

    /**
     * 비즈니스 로직
     */

    public void modified(ProductModifiedRequest request) {
        this.name = request.getName();
        if (request.getCategory() != category) {
            category = request.getCategory();
        }
    }
}
