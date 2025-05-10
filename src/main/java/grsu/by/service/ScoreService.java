package grsu.by.service;

import grsu.by.dto.ScoreDto;

import java.util.List;

public interface ScoreService {

    ScoreDto create(ScoreDto dto);

    ScoreDto findById(Long id);

    ScoreDto update(Long id, ScoreDto newDto);

    boolean deleteById(Long id);

    List<ScoreDto> findAll();

    List<ScoreDto> findBySolutionId(Long solutionId);

    List<ScoreDto> findByJudgeId(Long judgeId);
}
