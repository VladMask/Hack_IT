package grsu.by.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
    private Boolean isFinished = false;

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

    @Column(name = "result_announcement")
    private OffsetDateTime resultsAnnouncement;

    @OneToMany(mappedBy = "hackathon", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Feedback> feedbacks;

    @OneToMany(mappedBy = "hackathon", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<TeamHackathon> teamHackathons;

    @OneToMany(mappedBy = "hackathon", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Solution> solutions;

    @OneToMany(mappedBy = "hackathon", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Notification> notifications;

    @OneToMany(mappedBy = "hackathon", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Application> applications;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "user_hackathons",
            joinColumns = @JoinColumn(name = "user_role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "hackathon_id", referencedColumnName = "id")
    )
    private Set<User> judges;

    @OneToMany(mappedBy = "hackathon", fetch = FetchType.LAZY)
    private Set<Prize> prizes;

}
