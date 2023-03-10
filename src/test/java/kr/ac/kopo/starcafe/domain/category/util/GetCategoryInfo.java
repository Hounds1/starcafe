package kr.ac.kopo.starcafe.domain.category.util;

import kr.ac.kopo.starcafe.domain.category.dto.CategoryCreateRequest;
import kr.ac.kopo.starcafe.domain.category.dto.CategoryModifiedRequest;

public class GetCategoryInfo {

    public static CategoryCreateRequest getCreateRequest() {
        final String name = "testName";

        return CategoryCreateRequest
                .builder()
                .name(name)
                .build();
    }

    public static CategoryCreateRequest initCategory() {
        final String name = "initName";

        return CategoryCreateRequest
                .builder()
                .name(name)
                .build();
    }

    public static CategoryModifiedRequest getModifiedRequest() {
        final String name = "newName";

        return CategoryModifiedRequest
                .builder()
                .name(name)
                .build();
    }
}
