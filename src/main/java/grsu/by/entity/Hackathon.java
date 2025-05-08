package grsu.by.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hackathons")
public class Hackathon implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "start_date")
    private OffsetDateTime startDate;

    @Column(name = "end_date")
    private OffsetDateTime endDate;

    @Column(name = "photos_url")
    private String photosUrl;

    @Column(name = "is_finished")
    private Boolean isFinished;

    @Column(name = "registration_start")
    private OffsetDateTime registrationStart;

    @Column(name = "registration_end")
    private OffsetDateTime registrationEnd;

    @Column(name = "development_start")
    private OffsetDateTime developmentStart;

    @Column(name = "development_end")
    private OffsetDateTime developmentEnd;

    @Column(name = "assessment_start")
    private OffsetDateTime assessmentStart;

    @Column(name = "assessment_end")
    private OffsetDateTime assessmentEnd;

    @Column(name = "results_announcement")
    private OffsetDateTime resultsAnnouncement;

    @OneToMany(mappedBy = "hackathon", fetch = FetchType.LAZY)
    private Set<Feedback> feedbacks;

    @OneToMany(mappedBy = "hackathon", fetch = FetchType.LAZY)
    private Set<TeamHackathon> teamHackathons;

    @OneToMany(mappedBy = "hackathon", fetch = FetchType.LAZY)
    private Set<Solution> solutions;

    @OneToMany(mappedBy = "hackathon", fetch = FetchType.LAZY)
    private Set<Notification> notifications;

    @OneToMany(mappedBy = "hackathon", fetch = FetchType.LAZY)
    private Set<Application> applications;

    @OneToMany(mappedBy = "hackathon", fetch = FetchType.LAZY)
    private Set<UserHackathon> userHackathons;

    @OneToMany(mappedBy = "hackathon", fetch = FetchType.LAZY)
    private Set<Prize> prizes;

}
