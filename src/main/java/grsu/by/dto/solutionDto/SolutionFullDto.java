package grsu.by.dto.solutionDto;

import grsu.by.dto.scoreDto.ScoreCreationDto;
import grsu.by.dto.feedbackDto.FeedbackFullDto;
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
    private Boolean isGitRepository = false;
    private String repositoryUrl;
    private String filesUrl;
    private Integer totalScore;
    private OffsetDateTime checkedAt;
    private Set<FeedbackFullDto> feedbacks;
    private Set<ScoreCreationDto> scores;
}

