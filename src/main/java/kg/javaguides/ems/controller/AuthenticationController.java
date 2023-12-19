package kg.javaguides.ems.controller;

import kg.javaguides.ems.dto.JwtAuthenticationResponseDto;
import kg.javaguides.ems.dto.RefreshTokenRequestDto;
import kg.javaguides.ems.dto.SignInRequestDto;
import kg.javaguides.ems.dto.SignUpRequestDto;
import kg.javaguides.ems.entity.User;
import kg.javaguides.ems.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    @PostMapping("/signup")
    public ResponseEntity<User> signUp(@RequestBody SignUpRequestDto signUpRequestDto){
        return ResponseEntity.ok(authenticationService.signUp(signUpRequestDto));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponseDto> signIn(@RequestBody SignInRequestDto signInRequestDto){
        return ResponseEntity.ok(authenticationService.signIn(signInRequestDto));
    }
    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponseDto> refresh(@RequestBody RefreshTokenRequestDto refreshTokenRequestDto){
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequestDto));
    }
}
