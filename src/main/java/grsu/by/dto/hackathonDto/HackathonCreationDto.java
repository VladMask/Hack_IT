package grsu.by.dto.hackathonDto;

import grsu.by.dto.PrizeDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HackathonCreationDto implements Serializable {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotNull
    private OffsetDateTime startDate;
    @NotNull
    private OffsetDateTime endDate;
    private String photosUrl;
    private Boolean isFinished = false;
    @NotNull
    private OffsetDateTime registrationStart;
    @NotNull
    private OffsetDateTime registrationEnd;
    @NotNull
    private OffsetDateTime developmentStart;
    @NotNull
    private OffsetDateTime developmentEnd;
    @NotNull
    private OffsetDateTime assessmentStart;
    @NotNull
    private OffsetDateTime assessmentEnd;
    @NotNull
    private OffsetDateTime resultsAnnouncement;
    private Set<PrizeDto> prizes;
}


