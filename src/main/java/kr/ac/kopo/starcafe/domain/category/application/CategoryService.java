package kr.ac.kopo.starcafe.domain.category.application;

import kr.ac.kopo.starcafe.domain.category.dto.CategoryModifiedRequest;
import kr.ac.kopo.starcafe.domain.category.dto.SimpleCategoryResponse;
import kr.ac.kopo.starcafe.domain.category.error.MemberNotFoundException;
import kr.ac.kopo.starcafe.domain.category.model.Category;
import kr.ac.kopo.starcafe.domain.category.model.repository.CategoryRepository;
import kr.ac.kopo.starcafe.global.config.CustomModelMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CustomModelMapper customModelMapper;

    public SimpleCategoryResponse create(final Category category) {
        Category savedCategory = categoryRepository.save(category);
        Category findCategory = categoryRepository.findById(savedCategory.getId()).orElseThrow(RuntimeException::new);

        ModelMapper mapper = customModelMapper.standardMapper();

        return mapper.map(findCategory, SimpleCategoryResponse.class);
    }

    public SimpleCategoryResponse modified(final CategoryModifiedRequest request) {
        Long id = request.getId();
        Category findCategory = categoryRepository.findById(id).orElseThrow(
                () -> new MemberNotFoundException("찾을 수 없는 멤버")
        );

        findCategory.modified(request);

        ModelMapper mapper = customModelMapper.standardMapper();

        return mapper.map(findCategory, SimpleCategoryResponse.class);
    }

    public boolean remove(final Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(RuntimeException::new);
        categoryRepository.delete(category);

        return true;
    }

    public SimpleCategoryResponse findOne(final String name) {
        Category findCategory = categoryRepository.findByName(name).orElseThrow(RuntimeException::new);
        ModelMapper mapper = customModelMapper.standardMapper();

        return mapper.map(findCategory, SimpleCategoryResponse.class);
    }

    public List<SimpleCategoryResponse> findAll() {
        List<Category> all = categoryRepository.findAll();
        ModelMapper mapper = customModelMapper.standardMapper();

        return all.stream().map(c -> mapper.map(c, SimpleCategoryResponse.class)).toList();
    }
}
