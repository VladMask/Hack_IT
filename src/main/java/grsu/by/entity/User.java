package grsu.by.entity;

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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "username")
    private String username;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column(name = "is_active")
    private Boolean isActive = false;

    @Column(name = "created_at")
    @CreationTimestamp
    private OffsetDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private OffsetDateTime updatedAt;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
    private Credentials credentials;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<UserRole> userRoles;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<UserTeam> userTeams;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_hackathons",
            joinColumns = @JoinColumn(name = "user_role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "hackathon_id", referencedColumnName = "id")
    )
    private Set<Hackathon> hackathons;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_notifications",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "notification_id", referencedColumnName = "id")
    )
    private Set<Notification> notifications;

    @OneToMany(mappedBy = "judge", fetch = FetchType.LAZY)
    private Set<Feedback> feedbacks;

    @OneToMany(mappedBy = "judge", fetch = FetchType.LAZY)
    private Set<Score> scores;

}
