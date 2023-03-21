package kr.ac.kopo.starcafe.domain.product.application;

import kr.ac.kopo.starcafe.domain.product.model.Product;
import kr.ac.kopo.starcafe.domain.product.model.dto.ProductModifiedRequest;
import kr.ac.kopo.starcafe.domain.product.model.dto.SimpleProductResponse;
import kr.ac.kopo.starcafe.domain.product.model.repository.ProductRepository;
import kr.ac.kopo.starcafe.global.config.CustomModelMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final CustomModelMapper customModelMapper;

    public SimpleProductResponse create(final Product product) {
        Product savedProduct = productRepository.save(product);

        ModelMapper mapper = customModelMapper.standardMapper();

        return mapper.map(savedProduct, SimpleProductResponse.class);
    }

    public SimpleProductResponse modified(final ProductModifiedRequest request) {
        Product findProduct = productRepository.findById(request.getId()).orElseThrow(RuntimeException::new);
        findProduct.modified(request);

        ModelMapper mapper = customModelMapper.standardMapper();

        return mapper.map(findProduct, SimpleProductResponse.class);
    }

    public void remove(final Long id) {
        Product product = productRepository.findById(id).orElseThrow(RuntimeException::new);
        productRepository.delete(product);
    }
}
