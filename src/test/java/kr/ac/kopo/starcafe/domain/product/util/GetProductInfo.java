package kr.ac.kopo.starcafe.domain.product.util;

import kr.ac.kopo.starcafe.domain.product.model.Product;

public class GetProductInfo {

    public static Product initProduct() {
        final String name = "initProduct";

        return Product.builder()
                .name(name)
                .build();
    }
}
