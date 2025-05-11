package grsu.by.repository;

import grsu.by.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    Set<Feedback> findAllByJudgeId(Long judgeId);
    Set<Feedback> findAllBySolutionId(Long solutionId);
    Set<Feedback> findAllByHackathonId(Long hackathonId);
}
