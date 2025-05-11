package grsu.by.repository;

import grsu.by.entity.TeamHackathon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamHackathonRepository extends JpaRepository<TeamHackathon, Long> {

    Optional<TeamHackathon> findByTeamIdAndHackathonId(Long teamId, Long hackathonId);
    boolean existsByTeamIdAndHackathonId(Long teamId, Long hackathonId);

}
