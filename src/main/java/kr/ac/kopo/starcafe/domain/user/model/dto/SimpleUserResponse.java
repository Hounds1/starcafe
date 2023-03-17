package kr.ac.kopo.starcafe.domain.user.model.dto;

import kr.ac.kopo.starcafe.domain.user.model.User;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class SimpleUserResponse implements Serializable {

    private String email;
    private String firstName;
    private String lastName;

    private Address address;

    public static SimpleUserResponse of (User user) {
        return SimpleUserResponse.builder()
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .address(user.getAddress())
                .build();
    }
}
