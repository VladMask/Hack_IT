package grsu.by.service;

import grsu.by.dto.FeedbackDto;

import java.util.Set;

public interface FeedbackService {

    FeedbackDto create(FeedbackDto dto);

    FeedbackDto findById(Long id);

    FeedbackDto update(Long id, FeedbackDto newDto);

    boolean deleteById(Long id);

    Set<FeedbackDto> findByJudgeId(Long judgeId);

    Set<FeedbackDto> findBySolutionId(Long solutionId);

    Set<FeedbackDto> findByHackathonId(Long hackathonId);
}
