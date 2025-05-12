package grsu.by.service;

import grsu.by.dto.scoreDto.ScoreCreationDto;
import grsu.by.dto.scoreDto.ScoreFullDto;
import grsu.by.entity.ScoreId;

import java.util.Set;

public interface ScoreService {

    ScoreCreationDto create(ScoreCreationDto dto);

    ScoreFullDto findById(ScoreId id);

    ScoreFullDto update(ScoreId id, ScoreFullDto newDto);

    boolean deleteById(ScoreId id);

    Set<ScoreFullDto> findAll();

    Set<ScoreFullDto> findBySolutionId(Long solutionId);

    Set<ScoreFullDto> findByJudgeId(Long judgeId);
}
