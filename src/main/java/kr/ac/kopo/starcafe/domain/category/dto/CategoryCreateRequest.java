package kr.ac.kopo.starcafe.domain.category.dto;

import kr.ac.kopo.starcafe.domain.category.model.Category;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class CategoryCreateRequest {

    private String name;

    public Category toEntity() {
        LocalDate now = LocalDate.now();

        return Category
                .builder()
                .name(name)
                .regDate(now)
                .build();
    }
}
