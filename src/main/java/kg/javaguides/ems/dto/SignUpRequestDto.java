package kg.javaguides.ems.dto;

import lombok.Data;
import org.springframework.security.core.parameters.P;

import java.util.SplittableRandom;

@Data
public class SignUpRequestDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
