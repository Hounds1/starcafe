package kr.ac.kopo.starcafe.domain.user.application;

import kr.ac.kopo.starcafe.domain.user.model.User;
import kr.ac.kopo.starcafe.domain.user.model.dto.CreateUserRequest;
import kr.ac.kopo.starcafe.domain.user.model.dto.ModifiedUserRequest;
import kr.ac.kopo.starcafe.domain.user.model.dto.SimpleUserResponse;
import kr.ac.kopo.starcafe.domain.user.model.repository.UserRepository;
import kr.ac.kopo.starcafe.domain.user.util.GetUserInfo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@Transactional
public class MockUserServiceTest {

    @MockBean
    private UserRepository userRepository;

    @Test
    void createUserTest() {
        CreateUserRequest req = GetUserInfo.getDummyUser();
        User user = req.toEntity();

        String userPwd = user.getPassword();

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String encodedPassword = encoder.encode(user.getPassword());
        user.setEncodedPassword(encodedPassword);

        given(userRepository.save(user)).willReturn(user);
        User savedUser = userRepository.save(user);

        if (encoder.matches(userPwd, savedUser.getPassword())) {
            System.out.println("Password Encoded");
        }

        assertThat(savedUser.getPassword()).isEqualTo(encodedPassword);
    }

    @Test
    void ModifiedUserTest() {
        CreateUserRequest req = GetUserInfo.getDummyUser();
        User user = req.toEntity();

        String userPwd = user.getPassword();

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String encodedPassword = encoder.encode(user.getPassword());
        user.setEncodedPassword(encodedPassword);

        given(userRepository.save(user)).willReturn(user);
        User savedUser = userRepository.save(user);

        ModifiedUserRequest modifiedInfo = GetUserInfo.getModifiedInfo();
        String newPassword = encoder.encode(modifiedInfo.getPassword());

        savedUser.setEncodedPassword(newPassword);

        assertThat(encoder.matches(modifiedInfo.getPassword(), savedUser.getPassword())).isTrue();
    }

    @Test
    void findOneTest() {
        CreateUserRequest req = GetUserInfo.getDummyUser();
        User user = req.toEntity();

        String userPwd = user.getPassword();

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String encodedPassword = encoder.encode(user.getPassword());
        user.setEncodedPassword(encodedPassword);

        given(userRepository.save(user)).willReturn(user);
        User savedUser = userRepository.save(user);

        SimpleUserResponse res = SimpleUserResponse.of(savedUser);

        assertThat(res.getEmail()).isEqualTo(savedUser.getEmail());
    }
}
