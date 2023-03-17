package kr.ac.kopo.starcafe.domain.user.presentation;

import kr.ac.kopo.starcafe.domain.user.application.UserService;
import kr.ac.kopo.starcafe.domain.user.model.dto.CreateUserRequest;
import kr.ac.kopo.starcafe.domain.user.model.dto.ModifiedUserRequest;
import kr.ac.kopo.starcafe.domain.user.model.dto.SimpleUserResponse;
import kr.ac.kopo.starcafe.global.security.principal.CustomUserDetails;
import kr.ac.kopo.starcafe.global.security.principal.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping("/api/public/users")
    public ResponseEntity<SimpleUserResponse> create (@RequestBody final CreateUserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(request.toEntity()));
    }

    @GetMapping("/api/users/my")
    public ResponseEntity<SimpleUserResponse> getMyInfo () {
        return ResponseEntity.ok().body(userService.findMyInfo(getPrincipal()));
    }

    @GetMapping("/api/users")
    public ResponseEntity<SimpleUserResponse> getUserInfo(@RequestParam final Long id) {
        return ResponseEntity.ok().body(userService.findUserInfo(id));
    }

    @PutMapping("/api/users")
    public ResponseEntity<SimpleUserResponse> modified(@RequestBody final ModifiedUserRequest request) {
        return ResponseEntity.ok().body(userService.modified(request, getPrincipal()));
    }

    @DeleteMapping("/api/users")
    public ResponseEntity<Void> remove() {
        userService.remove(getPrincipal());
        return ResponseEntity.noContent().build();
    }

    public CustomUserDetails getPrincipal () {
        return (CustomUserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
    }
}
