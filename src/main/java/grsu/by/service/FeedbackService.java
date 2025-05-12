package grsu.by.service;

import grsu.by.dto.feedbackDto.FeedbackCreationDto;
import grsu.by.dto.feedbackDto.FeedbackFullDto;

import java.util.Set;

public interface FeedbackService {

    FeedbackCreationDto create(FeedbackCreationDto dto);

    FeedbackFullDto findById(Long id);

    FeedbackFullDto update(Long id, FeedbackFullDto newDto);

    boolean deleteById(Long id);

    Set<FeedbackFullDto> findByJudgeId(Long judgeId);

    Set<FeedbackFullDto> findBySolutionId(Long solutionId);

    Set<FeedbackFullDto> findByHackathonId(Long hackathonId);
}
