package grsu.by.service;

import grsu.by.dto.PrizeDto;

import java.util.List;

public interface PrizeService {

    PrizeDto create(PrizeDto dto);

    PrizeDto findById(Long id);

    PrizeDto update(Long id, PrizeDto newDto);

    boolean deleteById(Long id);

    List<PrizeDto> findAll();

    List<PrizeDto> findByHackathonId(Long hackathonId);

    List<PrizeDto> findByTeamId(Long teamId);
}