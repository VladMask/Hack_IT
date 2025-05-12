package grsu.by.dto.applicationDto;

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
public class ApplicationShortDto {
    private String projectDescription;
    private OffsetDateTime submittedAt;
    private Boolean isAccepted;

}
