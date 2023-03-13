package kr.ac.kopo.starcafe.domain.product.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kr.ac.kopo.starcafe.domain.category.model.Category;
import kr.ac.kopo.starcafe.domain.product.model.Product;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class SimpleProductResponse implements Serializable{

    private Long id;
    private String name;

    private Category category;

    public static SimpleProductResponse of (Product product) {
        return SimpleProductResponse
                .builder()
                .id(product.getId())
                .name(product.getName())
                .category(product.getCategory())
                .build();
    }
}
