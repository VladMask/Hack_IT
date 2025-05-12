package grsu.by.repository;

import grsu.by.entity.Prize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PrizeRepository extends JpaRepository<Prize, Long> {

    Set<Prize> findByHackathonId(Long hackathonId);

    @Query("""
        select p from Prize p
        where p.teamHackathon.team.id = :teamId
    """)
    Set<Prize> findByTeamId(@Param("teamId") Long teamId);

}
