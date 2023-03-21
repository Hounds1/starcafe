package kr.ac.kopo.starcafe.domain.user.presentation;

import kr.ac.kopo.starcafe.domain.user.application.UserReadService;
import kr.ac.kopo.starcafe.domain.user.application.UserService;
import kr.ac.kopo.starcafe.domain.user.model.dto.CreateUserRequest;
import kr.ac.kopo.starcafe.domain.user.model.dto.ModifiedUserRequest;
import kr.ac.kopo.starcafe.domain.user.model.dto.SimpleUserResponse;
import kr.ac.kopo.starcafe.global.security.principal.CustomUserDetails;
import kr.ac.kopo.starcafe.global.security.principal.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;
    private final UserReadService userReadService;

    @PostMapping("/api/public/users")
    public ResponseEntity<SimpleUserResponse> create (@RequestBody final CreateUserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(request.toEntity()));
    }

    @GetMapping("/api/users/my")
    public ResponseEntity<SimpleUserResponse> getMyInfo () {
        return ResponseEntity.ok().body(userReadService.findMyInfo(getPrincipal()));
    }

    @GetMapping("/api/users")
    public ResponseEntity<SimpleUserResponse> getUserInfo(@RequestParam final Long id) {
        return ResponseEntity.ok().body(userReadService.findOne(id));
    }

    @GetMapping("/api/users/all")
    public ResponseEntity<Page<SimpleUserResponse>> getAllUserInfo(@RequestParam final Integer page) {
        int size = 5;

        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("id"));
        return ResponseEntity.ok().body(userReadService.findAll(pageRequest));
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
