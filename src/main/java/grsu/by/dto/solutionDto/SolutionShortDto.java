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
public class SolutionShortDto {
    private Long id;
    @NotNull
    private Long hackathonId;
    private Long teamId;
    private Integer totalScore;
}
