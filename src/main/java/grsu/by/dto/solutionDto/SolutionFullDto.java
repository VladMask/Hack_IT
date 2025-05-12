package grsu.by.dto.solutionDto;

import grsu.by.dto.FeedbackDto;
import grsu.by.dto.ScoreDto;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    private Long hackathonId;

    private Long teamId;

    private Boolean isGitRepository = false;

    private String repositoryUrl;

    private String filesUrl;

    private Integer totalScore;

    private OffsetDateTime checkedAt;

    private Set<FeedbackDto> feedbacks;

    private Set<ScoreDto> scores;
}

