package grsu.by.repository;

import grsu.by.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    boolean existsByTeamIdAndHackathonId(Long teamId, Long hackathonId);

    @Query("""
        select a from Application a
                left join fetch a.team t
                left join fetch a.hackathon h
                where a.team.id = :teamId
    """)
    Set<Application> findAllByTeamId(Long teamId);

    @Query("""
        select a from Application a
                left join fetch a.team t
                left join fetch a.hackathon h
                where a.hackathon.id = :hackathonId
    """)
    Set<Application> findAllByHackathonId(Long hackathonId);

    @Query("""
        select a from Application a
                left join fetch a.team t
                left join fetch a.hackathon h
                where a.id = :id
    """)
    Optional<Application> findByIdWithDetails(Long id);
}
