package grsu.by.dto.applicationDto;

import grsu.by.dto.hackathonDto.HackathonShortDto;
import grsu.by.dto.teamDto.TeamCreationDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationFullDto {

    private HackathonShortDto hackathon;
    private TeamCreationDto team;
    private String projectDescription;
    private OffsetDateTime submittedAt;
    private Boolean isAccepted;

}
