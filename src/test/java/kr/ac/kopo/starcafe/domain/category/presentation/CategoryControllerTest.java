package kr.ac.kopo.starcafe.domain.category.presentation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.ac.kopo.starcafe.domain.category.application.CategoryService;
import kr.ac.kopo.starcafe.domain.category.dto.*;
import kr.ac.kopo.starcafe.domain.category.model.Category;
import kr.ac.kopo.starcafe.domain.category.util.GetCategoryInfo;
import org.junit.jupiter.api.BeforeEach;
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
class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CategoryService categoryService;

    private static final String BASE_URI = "/api/categories";

    @BeforeEach
    void initCategory() {
        CategoryCreateRequest request = GetCategoryInfo.initCategory();
        categoryService.create(request.toEntity());
    }

    @Test
    void createRequestTest() throws Exception {
        CategoryCreateRequest createRequest = GetCategoryInfo.getCreateRequest();
        ObjectMapper om = new ObjectMapper();

        String requestJson = om.writeValueAsString(createRequest);

        mockMvc.perform(post(BASE_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson)
                .characterEncoding("utf-8"))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    void ModifiedRequestTest() throws Exception{

        final Long id = 1L;
        final String name = "modifiedName";

        CategoryModifiedRequest modifiedRequest = CategoryModifiedRequest.builder()
                .id(id)
                .name(name)
                .build();

        ObjectMapper om = new ObjectMapper();

        String requestJson = om.writeValueAsString(modifiedRequest);

        mockMvc.perform(put(BASE_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void findAllTest() throws Exception{
        mockMvc.perform(get(BASE_URI + "/all"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void findOneTest() throws Exception{
        final String INIT_NAME = "initName";

        FindCategoryRequest req = FindCategoryRequest.builder()
                .name(INIT_NAME)
                .build();

        ObjectMapper om = new ObjectMapper();

        String requestJson = om.writeValueAsString(req);

        mockMvc.perform(get(BASE_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isOk())
                .andDo(print());
    }
}