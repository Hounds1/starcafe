package kr.ac.kopo.starcafe.domain.product.model.dto;

import kr.ac.kopo.starcafe.domain.category.model.Category;
import kr.ac.kopo.starcafe.domain.product.model.Product;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductCreateRequest {

    private String name;
    private Category category;


    public Product toEntity() {
        return Product.builder()
                .name(name)
                .category(category)
                .build();
    }
}
