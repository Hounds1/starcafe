package kr.ac.kopo.starcafe.domain.category.presentation;

import kr.ac.kopo.starcafe.domain.category.application.CategoryService;
import kr.ac.kopo.starcafe.domain.category.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/categories")
    public ResponseEntity<SimpleCategoryResponse> create(@RequestBody CategoryCreateRequest request) {
        SimpleCategoryResponse res = categoryService.create(request.toEntity());
        log.info("infos : [{}],[{}],[{}]", res.getId(), res.getName(), res.getRegDate());

        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @PutMapping("/categories")
    public ResponseEntity<SimpleCategoryResponse> modified(@RequestBody final CategoryModifiedRequest request) {
        return ResponseEntity.ok().body(categoryService.modified(request));
    }

    @DeleteMapping("/categories")
    public ResponseEntity<Void> remove(@RequestBody final CategoryRemoveRequest request) {
        categoryService.remove(request.getId());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/categories")
    public ResponseEntity<SimpleCategoryResponse> findOne(@RequestBody final FindCategoryRequest request) {
        return ResponseEntity.ok().body(categoryService.findOne(request.getName()));
    }

    @GetMapping("/categories/all")
    public ResponseEntity<List<SimpleCategoryResponse>> findAll() {
        return ResponseEntity.ok().body(categoryService.findAll());
    }
}
