package grsu.by.dto.feedbackDto;

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
public class FeedbackCreationDto {
    @NotBlank
    private String content;
    @NotNull
    private Long hackathonId;
    @NotNull
    private Long judgeId;
    @NotNull
    private Long solutionId;
}
