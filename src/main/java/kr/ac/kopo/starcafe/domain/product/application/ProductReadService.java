package kr.ac.kopo.starcafe.domain.product.application;

import kr.ac.kopo.starcafe.domain.product.model.Product;
import kr.ac.kopo.starcafe.domain.product.model.dto.SimpleProductResponse;
import kr.ac.kopo.starcafe.domain.product.model.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductReadService {

    private final ProductRepository productRepository;

    public SimpleProductResponse findOne(final Long id) {
        Product findProduct = productRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("상품을 찾을 수 없습니다.")
        );

        return SimpleProductResponse.of(findProduct);
    }

    public Page<SimpleProductResponse> findAll(final PageRequest pageRequest) {
        Page<SimpleProductResponse> list
                = productRepository.findAll(pageRequest).map(SimpleProductResponse::of);

        return list;
    }
}
