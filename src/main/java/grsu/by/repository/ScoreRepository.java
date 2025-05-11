package grsu.by.repository;

import grsu.by.entity.Score;
import grsu.by.entity.ScoreId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ScoreRepository extends JpaRepository<Score, ScoreId> {
    Set<Score> findBySolutionId(Long solutionId);
    Set<Score> findByJudgeId(Long judgeId);
}
