package grsu.by.dto.solutionDto;

import grsu.by.dto.teamDto.TeamCreationDto;
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
    private TeamCreationDto team;
    private Integer totalScore;
}
