package grsu.by.dto.hackathonDto;

import grsu.by.dto.ApplicationDto;
import grsu.by.dto.FeedbackDto;
import grsu.by.dto.NotificationDto;
import grsu.by.dto.PrizeDto;
import grsu.by.dto.TeamHackathonDto;
import grsu.by.dto.solutionDto.SolutionFullDto;
import grsu.by.dto.userDto.UserBaseDto;
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
public class HackathonFullDto implements Serializable {
    private Long id;
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
    private Set<FeedbackDto> feedbacks;
    private Set<TeamHackathonDto> teamHackathons;
    private Set<SolutionFullDto> solutions;
    private Set<NotificationDto> notifications;
    private Set<ApplicationDto> applications;
    private Set<UserBaseDto> judges;
    private Set<PrizeDto> prizes;

}

