package grsu.by.service;

import grsu.by.dto.ScoreDto;
import grsu.by.entity.ScoreId;

import java.util.Set;

public interface ScoreService {

    ScoreDto create(ScoreDto dto);

    ScoreDto findById(ScoreId id);

    ScoreDto update(ScoreId id, ScoreDto newDto);

    boolean deleteById(ScoreId id);

    Set<ScoreDto> findAll();

    Set<ScoreDto> findBySolutionId(Long solutionId);

    Set<ScoreDto> findByJudgeId(Long judgeId);
}
