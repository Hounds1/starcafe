package kr.ac.kopo.starcafe.domain.user.model.repository;

import kr.ac.kopo.starcafe.domain.user.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, CustomUserRepository {

    Page<User> findAll(Pageable pageable);

    Optional<User> findByEmail(final String email);
}