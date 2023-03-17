package kr.ac.kopo.starcafe.domain.auth.application;

import kr.ac.kopo.starcafe.domain.auth.dto.LoginRequest;
import kr.ac.kopo.starcafe.domain.auth.dto.SimpleLoginUserResponse;
import kr.ac.kopo.starcafe.domain.user.error.UserNotFoundException;
import kr.ac.kopo.starcafe.domain.user.model.User;
import kr.ac.kopo.starcafe.domain.user.model.repository.UserRepository;
import kr.ac.kopo.starcafe.global.security.principal.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SimpleLoginUserResponse login(final LoginRequest request) {
        User findUser = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException("존재하지 않는 유저입니다."));

        if (passwordEncoder.matches(request.getPassword(), findUser.getPassword()) && findUser.isActivated()) {
            CustomUserDetails userDetails = CustomUserDetails.of(findUser);

            UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(userDetails, "password", userDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(token);
        } else throw new IllegalStateException("계정을 사용할 수 없습니다.");

        return SimpleLoginUserResponse.of(findUser);
    }
}
