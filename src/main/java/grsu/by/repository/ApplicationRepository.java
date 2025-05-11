package grsu.by.repository;

import grsu.by.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    boolean existsByTeamIdAndHackathonId(Long teamId, Long hackathonId);
    Set<Application> findAllByTeamId(Long teamId);
    Set<Application> findAllByHackathonId(Long hackathonId);
}
