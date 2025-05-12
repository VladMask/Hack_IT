package grsu.by.dto.credentialsDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CredentialsBaseDto {
    @Email
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String phoneNumber;
    @NotNull
    private Long ownerId;
}
