package kg.javaguides.ems.service;

import kg.javaguides.ems.dto.JwtAuthenticationResponseDto;
import kg.javaguides.ems.dto.RefreshTokenRequestDto;
import kg.javaguides.ems.dto.SignInRequestDto;
import kg.javaguides.ems.dto.SignUpRequestDto;
import kg.javaguides.ems.entity.User;

public interface AuthenticationService {
    User signUp(SignUpRequestDto signUpRequestDto);
    JwtAuthenticationResponseDto signIn(SignInRequestDto signInRequestDto);
    JwtAuthenticationResponseDto refreshToken(RefreshTokenRequestDto refreshTokenRequestDto);
}
