package grsu.by.dto.teamDto;

import grsu.by.dto.UserTeamDto;
import grsu.by.dto.applicationDto.ApplicationCreationDto;
import grsu.by.dto.TeamHackathonDto;
import grsu.by.dto.solutionDto.SolutionFullDto;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeamFullDto {
    @NotBlank
    private String name;
    private Boolean isActive = false;
    private Set<UserTeamDto> userTeams;
    private Set<TeamHackathonDto> teamHackathons;
    private Set<SolutionFullDto> solutions;
    private Set<ApplicationCreationDto> applications;
}

