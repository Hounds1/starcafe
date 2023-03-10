package kr.ac.kopo.starcafe.domain.category.dto;

import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class FindCategoryRequest {

    private String name;
}
