package grsu.by.dto.feedbackDto;

import grsu.by.dto.solutionDto.SolutionShortDto;
import grsu.by.dto.userDto.UserBaseDto;
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
public class FeedbackShortDto {
    private String content;
    private UserBaseDto judge;
    private SolutionShortDto solution;
}
