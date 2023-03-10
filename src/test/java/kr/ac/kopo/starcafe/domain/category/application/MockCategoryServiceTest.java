package kr.ac.kopo.starcafe.domain.category.application;

import kr.ac.kopo.starcafe.domain.category.dto.CategoryCreateRequest;
import kr.ac.kopo.starcafe.domain.category.dto.CategoryModifiedRequest;
import kr.ac.kopo.starcafe.domain.category.model.Category;
import kr.ac.kopo.starcafe.domain.category.model.repository.CategoryRepository;
import kr.ac.kopo.starcafe.domain.category.repository.MockCategoryRepository;
import kr.ac.kopo.starcafe.domain.category.util.GetCategoryInfo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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
public class MockCategoryServiceTest {

    @MockBean(name = "mockCategoryRepository")
    private MockCategoryRepository mockCategoryRepository;


    @Test
    @DisplayName("카테고리 생성")
    void CategoryCreateRequest() {
        final String name = "testName";

        CategoryCreateRequest request = GetCategoryInfo.getCreateRequest();

        Category category = request.toEntity();

        given(mockCategoryRepository.save(category)).willReturn(category);
        Category savedCategory = mockCategoryRepository.save(category);

        assertThat(savedCategory.getName()).isEqualTo(category.getName());
        assertThat(savedCategory.getRegDate()).isEqualTo(category.getRegDate());
    }

    @Test
    @DisplayName("카테고리 수정")
    void CategoryModifiedRequest() {
        CategoryCreateRequest createRequest = GetCategoryInfo.getCreateRequest();
        Category category = createRequest.toEntity();

        given(mockCategoryRepository.save(category)).willReturn(category);
        Category savedCategory = mockCategoryRepository.save(category);

        final String name = "newName";

        CategoryModifiedRequest request = CategoryModifiedRequest
                .builder()
                .name(name)
                .build();

        savedCategory.modified(request);

        assertThat(savedCategory.getName()).isEqualTo(name);
    }

    @Test
    @DisplayName("카테고리 단일 조회")
    void getCategoryInfoOne() {
        CategoryCreateRequest request = GetCategoryInfo.getCreateRequest();
        Category category = request.toEntity();

        given(mockCategoryRepository.save(category)).willReturn(category);
        Category savedCategory = mockCategoryRepository.save(category);

        given(mockCategoryRepository.findById(savedCategory.getId())).willReturn(Optional.of(category));
        Category findCategory = mockCategoryRepository.findById(category.getId()).orElseThrow(RuntimeException::new);

        assertThat(findCategory.getName()).isEqualTo(request.getName());
    }

    @Test
    @DisplayName("카테고리 전체 조회")
    void getCategoryInfoAll() {
        final String name = "second";

        CategoryCreateRequest request1 = GetCategoryInfo.getCreateRequest();
        CategoryCreateRequest request2 = CategoryCreateRequest
                .builder()
                .name(name)
                .build();

        Category category1 = request1.toEntity();
        Category category2 = request2.toEntity();

        List<Category> list = new ArrayList<>();

        given(mockCategoryRepository.save(category1)).willReturn(category1);
        given(mockCategoryRepository.save(category2)).willReturn(category2);
        Category savedCategory1 = mockCategoryRepository.save(category1);
        Category savedCategory2 = mockCategoryRepository.save(category2);

        list.add(savedCategory1);
        list.add(savedCategory2);

        given(mockCategoryRepository.findAll()).willReturn(list);
        List<Category> all = mockCategoryRepository.findAll();

        assertThat(all.size()).isEqualTo(2);
    }
}
