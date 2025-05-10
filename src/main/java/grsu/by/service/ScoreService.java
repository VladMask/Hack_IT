package grsu.by.service;

import grsu.by.dto.ScoreDto;

public interface ScoreService {

    ScoreDto create(ScoreDto dto);

    ScoreDto findById(Long id);

    ScoreDto update(Long id, ScoreDto newDto);

    boolean deleteById(Long id);

}
