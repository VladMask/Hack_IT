package grsu.by.dto.scoreDto;

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
public class ScoreCreationDto {
    @NotNull
    private Long solutionId;
    @NotNull
    private Long judgeId;
    @NotNull
    private Integer value;
}
