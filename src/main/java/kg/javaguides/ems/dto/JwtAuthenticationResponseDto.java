package kg.javaguides.ems.dto;

import lombok.Data;

@Data
public class JwtAuthenticationResponseDto {

    private String token;
    private String refreshToken;
}
