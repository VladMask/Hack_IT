package grsu.by.dto.solutionDto;

import grsu.by.dto.feedbackDto.FeedbackCreationDto;
import grsu.by.dto.hackathonDto.HackathonShortDto;
import grsu.by.dto.teamDto.TeamCreationDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SolutionFullDto {

    private HackathonShortDto hackathon;
    private TeamCreationDto team;
    private OffsetDateTime submittedAt;
    private Boolean isGitRepository = false;
    private String repositoryUrl;
    private String filesUrl;
    private Integer totalScore;
    private OffsetDateTime checkedAt;
    private Set<FeedbackCreationDto> feedbacks;
    private Set<SolutionShortDto> scores;
}

