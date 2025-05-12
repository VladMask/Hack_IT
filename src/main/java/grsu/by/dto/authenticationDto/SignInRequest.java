package grsu.by.dto.authenticationDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignInRequest {
    @Size(min = 5)
    @NotBlank
    private String email;

    @Size(min = 6, max = 16)
    @NotBlank
    private String password;
}
