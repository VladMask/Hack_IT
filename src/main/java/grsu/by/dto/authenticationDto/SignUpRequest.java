package grsu.by.dto.authenticationDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import grsu.by.dto.credentialsDto.CredentialsCreationDto;
import grsu.by.dto.userDto.UserCreationDto;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {
    UserCreationDto userCreationDto;
    CredentialsCreationDto credentialsDto;
}
