package kr.ac.kopo.starcafe.domain.user.model.repository;

import kr.ac.kopo.starcafe.domain.user.model.dto.SimpleUserResponse;
import kr.ac.kopo.starcafe.global.security.principal.CustomUserDetails;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface CustomUserRepository {

    Optional<CustomUserDetails> findCustomDetailsById(final Long id);

    Optional<SimpleUserResponse> findUserByKeyword(final String keyword);
}
