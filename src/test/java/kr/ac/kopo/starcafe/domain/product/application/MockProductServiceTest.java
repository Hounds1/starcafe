package kr.ac.kopo.starcafe.domain.product.application;

import kr.ac.kopo.starcafe.domain.product.model.Product;
import kr.ac.kopo.starcafe.domain.product.model.dto.Category;
import kr.ac.kopo.starcafe.domain.product.model.dto.ProductCreateRequest;
import kr.ac.kopo.starcafe.domain.product.model.dto.ProductModifiedRequest;
import kr.ac.kopo.starcafe.domain.product.repository.MockProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@Transactional
class MockProductServiceTest {

    @MockBean
    private MockProductRepository mockProductRepository;

    @Test
    @DisplayName(value = "생성 요청")
    void createProductTest() {

        ProductCreateRequest request = ProductCreateRequest.builder()
                .name("initName")
                .category(Category.ETC)
                .build();

        Product product = request.toEntity();

        given(mockProductRepository.save(product)).willReturn(product);

        Product savedProduct = mockProductRepository.save(product);

        assertThat(savedProduct.getName()).isEqualTo(product.getName());
        assertThat(savedProduct.getCategory()).isEqualTo(product.getCategory());
    }

    @Test
    @DisplayName(value = "수정 요청")
    void modifiedProductTest() {

        ProductCreateRequest req = ProductCreateRequest.builder()
                .name("initName")
                .category(Category.ETC)
                .build();

        Product product = req.toEntity();

        given(mockProductRepository.save(product)).willReturn(product);
        Product savedProduct = mockProductRepository.save(product);

        ProductModifiedRequest modReq = ProductModifiedRequest.builder()
                .name("newName")
                .category(Category.COFFEE)
                .build();

        savedProduct.modified(modReq);

        assertThat(savedProduct.getName()).isEqualTo(modReq.getName());
        assertThat(savedProduct.getCategory()).isEqualTo(Category.COFFEE);
    }

    @Test
    @DisplayName(value = "단일 조회")
    void findOneTest() {

        ProductCreateRequest req = ProductCreateRequest.builder()
                .name("initName")
                .category(Category.ETC)
                .build();

        Product product = req.toEntity();

        given(mockProductRepository.save(product)).willReturn(product);
        Product savedProduct = mockProductRepository.save(product);

        given(mockProductRepository.findByName(savedProduct.getName())).willReturn(Optional.of(savedProduct));
        Product findProduct = mockProductRepository.findByName(savedProduct.getName()).get();

        assertThat(findProduct.getName()).isEqualTo(savedProduct.getName());
    }

    @Test
    @DisplayName(value = "전체 조회")
    void findAllTest() {

        ProductCreateRequest req = ProductCreateRequest.builder()
                .name("initName")
                .category(Category.ETC)
                .build();

        Product product = req.toEntity();

        given(mockProductRepository.save(product)).willReturn(product);
        Product savedProduct = mockProductRepository.save(product);

        List<Product> list = new ArrayList<>();
        list.add(savedProduct);

        given(mockProductRepository.findAll()).willReturn(list);
        List<Product> all = mockProductRepository.findAll();

        assertThat(all.size()).isEqualTo(1);
    }

}
