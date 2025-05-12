package grsu.by.dto.applicationDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationCreationDto {

    @NotNull
    private Long hackathonId;
    @NotNull
    private Long teamId;
    @NotBlank
    private String projectDescription;
}
