package kr.ac.kopo.starcafe.domain.category.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class CategoryRemoveRequest {

    private Long id;
}
