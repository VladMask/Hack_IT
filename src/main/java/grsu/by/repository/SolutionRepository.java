package grsu.by.repository;

import grsu.by.entity.Solution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface SolutionRepository extends JpaRepository<Solution, Long> {
    Set<Solution> findByTeamId(Long teamId);
    Set<Solution> findByHackathonId(Long hackathonId);

    @Query("""
        select s from Solution s
                left join fetch s.team t
                left join fetch s.hackathon h
                left join fetch s.feedbacks f
                left join fetch s.scores sc
                where s.id = :id
    """)
    Optional<Solution> findByIdWithDetails(Long id);
}
