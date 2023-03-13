package kr.ac.kopo.starcafe.domain.category.model;

import kr.ac.kopo.starcafe.domain.category.dto.CategoryModifiedRequest;
import kr.ac.kopo.starcafe.domain.product.model.Product;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Category {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @Column(name = "category_name")
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate regDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate lastModifiedDate;

    @OneToMany(mappedBy = "category")
    private List<Product> products = new ArrayList<>();

    /**
     * 비즈니스 로직
     */

    public void modified(CategoryModifiedRequest request) {
        this.name = request.getName();

        LocalDate now = LocalDate.now();
        this.lastModifiedDate = now;
    }
}
