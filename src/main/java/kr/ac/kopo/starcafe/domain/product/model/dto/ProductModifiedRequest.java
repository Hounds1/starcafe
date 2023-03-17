package kr.ac.kopo.starcafe.domain.product.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductModifiedRequest {
    private Long id;
    private String name;
    private Category category;
}
