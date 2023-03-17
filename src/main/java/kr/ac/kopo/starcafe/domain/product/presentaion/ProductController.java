package kr.ac.kopo.starcafe.domain.product.presentaion;

import kr.ac.kopo.starcafe.domain.product.application.ProductService;
import kr.ac.kopo.starcafe.domain.product.model.Product;
import kr.ac.kopo.starcafe.domain.product.model.dto.ProductCreateRequest;
import kr.ac.kopo.starcafe.domain.product.model.dto.ProductModifiedRequest;
import kr.ac.kopo.starcafe.domain.product.model.dto.SimpleProductResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class ProductController {

    private final ProductService productService;

    @PostMapping("/products")
    public ResponseEntity<SimpleProductResponse> create(@RequestBody final ProductCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.create(request.toEntity()));
    }

    @PutMapping("/products")
    public ResponseEntity<SimpleProductResponse> modified(@RequestBody final ProductModifiedRequest request) {
        return ResponseEntity.ok().body(productService.modified(request));
    }

    @DeleteMapping("/products")
    public ResponseEntity<Void> remove(@RequestBody final Long id) {
        productService.remove(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/products")
    public ResponseEntity<SimpleProductResponse> findOne(@RequestParam(value = "id") final Long id) {
        return ResponseEntity.ok().body(productService.findOne(id));
    }

    @GetMapping("/products/all")
    public ResponseEntity<Page<SimpleProductResponse>> findAll(@RequestParam(value = "page") Integer page) {
        int size = 5;

        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("id"));

        return ResponseEntity.ok().body(productService.findAll(pageRequest));
    }
}