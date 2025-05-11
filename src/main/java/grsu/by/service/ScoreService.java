package grsu.by.service;

import grsu.by.dto.ScoreDto;

import java.util.Set;

public interface ScoreService {

    ScoreDto create(ScoreDto dto);

    ScoreDto findById(Long id);

    ScoreDto update(Long id, ScoreDto newDto);

    boolean deleteById(Long id);

    Set<ScoreDto> findAll();

    Set<ScoreDto> findBySolutionId(Long solutionId);

    Set<ScoreDto> findByJudgeId(Long judgeId);
}
