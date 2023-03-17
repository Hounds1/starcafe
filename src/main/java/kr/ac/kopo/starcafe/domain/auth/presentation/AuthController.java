package kr.ac.kopo.starcafe.domain.auth.presentation;

import kr.ac.kopo.starcafe.domain.auth.application.AuthService;
import kr.ac.kopo.starcafe.domain.auth.dto.LoginRequest;
import kr.ac.kopo.starcafe.domain.auth.dto.SimpleLoginUserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;

    @PostMapping("/api/public/auth")
    public ResponseEntity<SimpleLoginUserResponse> login (@RequestBody LoginRequest request) {
        return ResponseEntity.ok().body(authService.login(request));
    }


    @DeleteMapping("/api/auth")
    public ResponseEntity<Void> logout () {
        return ResponseEntity.ok().build();
    }

}
