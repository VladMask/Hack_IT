package grsu.by.dto.feedbackDto;

import grsu.by.dto.hackathonDto.HackathonShortDto;
import grsu.by.dto.solutionDto.SolutionShortDto;
import grsu.by.dto.userDto.UserBaseDto;
import jakarta.validation.constraints.NotBlank;
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
public class FeedbackFullDto {
    private String content;
    private HackathonShortDto hackathon;
    private UserBaseDto judge;
    private SolutionShortDto solution;
}
