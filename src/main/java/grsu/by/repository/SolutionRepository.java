package grsu.by.repository;

import grsu.by.entity.Solution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface SolutionRepository extends JpaRepository<Solution, Long> {
    Set<Solution> findByTeamId(Long teamId);
    Set<Solution> findByHackathonId(Long hackathonId);
}
