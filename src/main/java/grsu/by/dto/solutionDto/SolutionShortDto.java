package grsu.by.dto.solutionDto;

import grsu.by.dto.hackathonDto.HackathonShortDto;
import grsu.by.dto.teamDto.TeamCreationDto;
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
    @NotNull
    private HackathonShortDto hackathon;
    private TeamCreationDto team;
    private Integer totalScore;
}
