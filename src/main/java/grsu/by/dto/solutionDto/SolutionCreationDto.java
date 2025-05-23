package grsu.by.dto.solutionDto;

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
public class SolutionCreationDto {
    @NotNull
    private Long hackathonId;
    @NotNull
    private Long teamId;
    private Boolean isGitRepository = false;
    private String repositoryUrl;
    private String filesUrl;
}


