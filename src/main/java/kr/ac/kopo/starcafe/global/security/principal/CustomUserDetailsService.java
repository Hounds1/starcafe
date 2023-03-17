package kr.ac.kopo.starcafe.global.security.principal;

import kr.ac.kopo.starcafe.domain.user.error.UserNotFoundException;
import kr.ac.kopo.starcafe.domain.user.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public CustomUserDetails loadUserByUsername(final String id) throws UsernameNotFoundException {
        Long userId = Long.parseLong(id);
        return userRepository.findCustomDetailsById(userId).orElseThrow(
                () -> new UserNotFoundException("존재하지 않는 유저")
        );
    }
}
