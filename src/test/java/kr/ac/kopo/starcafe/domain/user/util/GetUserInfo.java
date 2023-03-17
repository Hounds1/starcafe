package kr.ac.kopo.starcafe.domain.user.util;

import kr.ac.kopo.starcafe.domain.user.model.dto.Address;
import kr.ac.kopo.starcafe.domain.user.model.dto.CreateUserRequest;
import kr.ac.kopo.starcafe.domain.user.model.dto.ModifiedUserRequest;

public class GetUserInfo {

    public static CreateUserRequest getDummyUser() {
        Address address = Address.builder()
                .city("대전광역시")
                .road("우암로")
                .zipcode("352-21")
                .build();

        final String email = "test@email.com";
        final String password = "test";
        final String firstName = "te";
        final String lastName = "st";

        return CreateUserRequest.builder()
                .email(email)
                .password(password)
                .firstName(firstName)
                .lastName(lastName)
                .address(address)
                .build();

    }

    public static ModifiedUserRequest getModifiedInfo() {
        Address address = Address.builder()
                .city("대전광역시")
                .road("우암로")
                .zipcode("352-23")
                .build();

        final String password = "testst";
        final String firstName = "st";
        final String lastName = "te";

        return ModifiedUserRequest.builder()
                .password(password)
                .firstName(firstName)
                .lastName(lastName)
                .address(address)
                .build();
    }
}
