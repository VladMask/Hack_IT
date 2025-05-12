package grsu.by.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class ApplicationDto {

    @NotNull
    private Long hackathonId;
    @NotNull
    private Long teamId;
    @NotBlank
    private String projectDescription;

    private OffsetDateTime submittedAt;

    private Boolean isAccepted = false;
}
