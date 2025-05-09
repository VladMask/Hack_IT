package grsu.by.dto.userDto;

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
public class UserBaseDto {
    private Long id;
    @NotBlank
    private String firstname;
    @NotBlank
    private String lastname;
    @NotNull
    private String username;
}
