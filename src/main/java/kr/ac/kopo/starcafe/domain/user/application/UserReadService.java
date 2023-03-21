package kr.ac.kopo.starcafe.domain.user.application;

import kr.ac.kopo.starcafe.domain.user.error.UserNotFoundException;
import kr.ac.kopo.starcafe.domain.user.model.User;
import kr.ac.kopo.starcafe.domain.user.model.dto.SimpleUserResponse;
import kr.ac.kopo.starcafe.domain.user.model.repository.UserRepository;
import kr.ac.kopo.starcafe.global.security.principal.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserReadService {

    private final UserRepository userRepository;

    public SimpleUserResponse findOne(final Long id) {
        User findUser = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("존재하지 않는 유저")
        );

        return SimpleUserResponse.of(findUser);
    }

    public SimpleUserResponse findMyInfo(final CustomUserDetails details) {
        User findUser = userRepository.findById(details.getId()).orElseThrow(
                () -> new IllegalStateException("현재 조회가 불가능한 상태")
        );

        return SimpleUserResponse.of(findUser);
    }

    public Page<SimpleUserResponse> findAll(final PageRequest pageRequest) {
        Page<SimpleUserResponse> list = userRepository.findAll(pageRequest).map(SimpleUserResponse::of);

        return list;
    }
}
