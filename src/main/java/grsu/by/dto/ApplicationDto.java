package grsu.by.dto;

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

    private Long id;

    private Long hackathonId;

    private Long teamId;

    private String projectDescription;

    private OffsetDateTime submittedAt;

    private Boolean isAccepted = false;
}
