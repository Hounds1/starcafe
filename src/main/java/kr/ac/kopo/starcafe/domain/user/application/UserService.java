package kr.ac.kopo.starcafe.domain.user.application;

import kr.ac.kopo.starcafe.domain.user.error.AlreadyExpiredUserException;
import kr.ac.kopo.starcafe.domain.user.error.UserNotFoundException;
import kr.ac.kopo.starcafe.domain.user.model.User;
import kr.ac.kopo.starcafe.domain.user.model.dto.ModifiedUserRequest;
import kr.ac.kopo.starcafe.domain.user.model.dto.SimpleUserResponse;
import kr.ac.kopo.starcafe.domain.user.model.repository.UserRepository;
import kr.ac.kopo.starcafe.global.security.principal.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public SimpleUserResponse create (final User user) {
        String encodePass = passwordEncoder.encode(user.getPassword());
        user.setEncodedPassword(encodePass);

        User savedUser = userRepository.save(user);

        return SimpleUserResponse.of(savedUser);
    }

    public SimpleUserResponse modified (final ModifiedUserRequest request, final CustomUserDetails userDetails) {
        User baseInfo = userRepository.findById(userDetails.getId())
                .orElseThrow(() -> new IllegalStateException("요청을 수행할 수 없음."));

        if (!request.getPassword().isBlank()) {
            String encodePass = passwordEncoder.encode(request.getPassword());
            baseInfo.setEncodedPassword(encodePass);
            baseInfo.modified(request);
        }

        return SimpleUserResponse.of(baseInfo);
    }

    public void remove(final CustomUserDetails userDetails) {
        User findMember = userRepository.findById(userDetails.getId())
                .orElseThrow(() -> new IllegalStateException("탈퇴 가능한 상태가 아닙니다."));

        if (findMember.isActivated()) {
            findMember.expiredAccount();
        } else {
            throw new AlreadyExpiredUserException("이미 탈퇴된 유저입니다.");
        }
    }

    @Transactional(readOnly = true)
    public SimpleUserResponse findUserInfo (final Long id) {
        User findUser = userRepository.findById(id)
                .orElseThrow( () -> new IllegalStateException("유저 정보를 불러올 수 없습니다."));

        return SimpleUserResponse.of(findUser);
    }

    @Transactional(readOnly = true)
    public SimpleUserResponse findMyInfo (final CustomUserDetails userDetails) {
        User findUser = userRepository.findById(userDetails.getId())
                .orElseThrow(() -> new UserNotFoundException("존재하지 않는 유저입니다"));

        return SimpleUserResponse.of(findUser);
    }

    @Transactional(readOnly = true)
    public Page<SimpleUserResponse> findAll (final PageRequest pageRequest) {
        return userRepository.findAll(pageRequest).map(SimpleUserResponse::of);
    }
}
