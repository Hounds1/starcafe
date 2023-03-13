package kr.ac.kopo.starcafe.domain.product.presentaion;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import kr.ac.kopo.starcafe.domain.category.application.CategoryService;
import kr.ac.kopo.starcafe.domain.category.dto.CategoryCreateRequest;
import kr.ac.kopo.starcafe.domain.category.model.Category;
import kr.ac.kopo.starcafe.domain.category.model.repository.CategoryRepository;
import kr.ac.kopo.starcafe.domain.category.util.GetCategoryInfo;
import kr.ac.kopo.starcafe.domain.product.application.ProductService;
import kr.ac.kopo.starcafe.domain.product.model.Product;
import kr.ac.kopo.starcafe.domain.product.model.dto.ProductCreateRequest;
import kr.ac.kopo.starcafe.domain.product.model.dto.ProductModifiedRequest;
import kr.ac.kopo.starcafe.domain.product.model.repository.ProductRepository;
import kr.ac.kopo.starcafe.domain.product.util.GetProductInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    private static final String INIT_NAME = "initName";
    private static final String MODIFIED_NAME = "newInitName";

    @Test
    @DisplayName(value = "생성 요청")
    void mvcCreateTest() throws Exception {
        CategoryCreateRequest createRequest = GetCategoryInfo.getCreateRequest();
        Category savedCategory = categoryRepository.save(createRequest.toEntity());

        ProductCreateRequest req = ProductCreateRequest.builder()
                .name(INIT_NAME)
                .category(savedCategory)
                .build();

        ObjectMapper om = new ObjectMapper();

        String reqJson = om.registerModule(new JavaTimeModule()).writeValueAsString(req);

        mockMvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(reqJson))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    @DisplayName(value = "수정 요청")
    void mvcModifiedTest() throws Exception {
        CategoryCreateRequest createRequest = GetCategoryInfo.getCreateRequest();
        Category savedCategory = categoryRepository.save(createRequest.toEntity());

        ProductCreateRequest req = ProductCreateRequest.builder()
                .name(INIT_NAME)
                .category(savedCategory)
                .build();

        Product savedProduct = productRepository.save(req.toEntity());

        ProductModifiedRequest modReq = ProductModifiedRequest.builder()
                .id(savedProduct.getId())
                .name(MODIFIED_NAME)
                .category(savedCategory)
                .build();

        ObjectMapper om = new ObjectMapper();

        String modReqJSON = om.registerModule(new JavaTimeModule()).writeValueAsString(modReq);

        mockMvc.perform(put("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(modReqJSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Transactional(readOnly = true)
    @DisplayName(value = "단건 조회")
    void mvcFindOneTest() throws Exception{

        CategoryCreateRequest createRequest = GetCategoryInfo.getCreateRequest();
        Category savedCategory = categoryRepository.save(createRequest.toEntity());

        ProductCreateRequest req = ProductCreateRequest.builder()
                .name(INIT_NAME)
                .category(savedCategory)
                .build();

        productRepository.save(req.toEntity());

        mockMvc.perform(get("/api/products?id=1"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Transactional(readOnly = true)
    @DisplayName(value = "전체 조회")
    void mvcFindAllTest() throws Exception{
        CategoryCreateRequest createRequest = GetCategoryInfo.getCreateRequest();
        Category savedCategory = categoryRepository.save(createRequest.toEntity());

        ProductCreateRequest req = ProductCreateRequest.builder()
                .name(INIT_NAME)
                .category(savedCategory)
                .build();

        ProductCreateRequest req2 = ProductCreateRequest.builder()
                .name("INIT_NAME")
                .category(savedCategory)
                .build();

        productRepository.save(req.toEntity());
        productRepository.save(req2.toEntity());

        mockMvc.perform(get("/api/products/all"))
                .andExpect(status().isOk())
                .andDo(print());
    }
}