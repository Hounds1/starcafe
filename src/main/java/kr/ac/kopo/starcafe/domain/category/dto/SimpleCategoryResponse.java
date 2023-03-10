package kr.ac.kopo.starcafe.domain.category.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class SimpleCategoryResponse implements Serializable {

    private Long id;
    private String name;
    private LocalDate regDate;

    private LocalDate lastModifiedDate;
}
