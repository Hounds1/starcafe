package kr.ac.kopo.starcafe.domain.user.model.dto;

import kr.ac.kopo.starcafe.domain.user.model.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class CreateUserRequest {


    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private Address address;


    public User toEntity() {
        return User.builder()
                .email(email)
                .password(password)
                .firstName(lastName)
                .address(address)
                .roleType(RoleType.USER)
                .activated(true)
                .build();
    }
}
