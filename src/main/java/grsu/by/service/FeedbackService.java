package grsu.by.service;

import grsu.by.dto.FeedbackDto;

import java.util.List;

public interface FeedbackService {

    FeedbackDto create(FeedbackDto dto);

    FeedbackDto findById(Long id);

    FeedbackDto update(Long id, FeedbackDto newDto);

    boolean deleteById(Long id);

    List<FeedbackDto> findByJudgeId(Long judgeId);

    List<FeedbackDto> findBySolutionId(Long solutionId);

    List<FeedbackDto> findByHackathonId(Long hackathonId);
}
