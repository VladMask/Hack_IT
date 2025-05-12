package grsu.by.dto.scoreDto;

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
public class ScoreFullDto {
    private SolutionShortDto solution;
    private UserBaseDto judge;
    private Integer value;
}
