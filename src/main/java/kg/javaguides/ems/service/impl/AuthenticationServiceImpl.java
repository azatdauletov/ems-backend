package kg.javaguides.ems.service.impl;

import kg.javaguides.ems.dto.JwtAuthenticationResponseDto;
import kg.javaguides.ems.dto.RefreshTokenRequestDto;
import kg.javaguides.ems.dto.SignInRequestDto;
import kg.javaguides.ems.dto.SignUpRequestDto;
import kg.javaguides.ems.entity.Role;
import kg.javaguides.ems.entity.User;
import kg.javaguides.ems.repository.UserRepository;
import kg.javaguides.ems.service.AuthenticationService;
import kg.javaguides.ems.service.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    public User signUp(SignUpRequestDto signUpRequestDto){
        User user = new User();

        user.setEmail(signUpRequestDto.getEmail());
        user.setFirstName(signUpRequestDto.getFirstName());
        user.setLastName(signUpRequestDto.getLastName());
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(signUpRequestDto.getPassword()));

        return userRepository.save(user);
    }

    public JwtAuthenticationResponseDto signIn(SignInRequestDto signInRequestDto){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequestDto.getEmail(),
                signInRequestDto.getPassword()));
        var user = userRepository.findByEmail(signInRequestDto.getEmail())
                .orElseThrow(()-> new IllegalArgumentException("Invalid email or password"));
        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);
        JwtAuthenticationResponseDto jwtAuthenticationResponseDto = new JwtAuthenticationResponseDto();
        jwtAuthenticationResponseDto.setToken(jwt);
        jwtAuthenticationResponseDto.setRefreshToken(refreshToken);
        return jwtAuthenticationResponseDto;
    }

    public JwtAuthenticationResponseDto refreshToken(RefreshTokenRequestDto refreshTokenRequestDto){
        String userEmail = jwtService.extractUserName(refreshTokenRequestDto.getToken());
        User user = userRepository.findByEmail(userEmail).orElseThrow();
        if(jwtService.isTokenValid(refreshTokenRequestDto.getToken(), user)){
            var jwt = jwtService.generateToken(user);

            JwtAuthenticationResponseDto jwtAuthenticationResponseDto = new JwtAuthenticationResponseDto();
            jwtAuthenticationResponseDto.setToken(jwt);
            jwtAuthenticationResponseDto.setRefreshToken(refreshTokenRequestDto.getToken());
            return jwtAuthenticationResponseDto;
        }
        return null;
    }
}
