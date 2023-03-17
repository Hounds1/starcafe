package kr.ac.kopo.starcafe.domain.auth.dto;

import kr.ac.kopo.starcafe.domain.user.model.User;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class SimpleLoginUserResponse implements Serializable {

    private String firstName;

    private String lastName;

    public static SimpleLoginUserResponse of (final User user) {
        return SimpleLoginUserResponse
                .builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }
}
