package grsu.by.service;

import grsu.by.dto.PrizeDto;

import java.util.Set;

public interface PrizeService {

    PrizeDto create(PrizeDto dto);

    PrizeDto findById(Long id);

    PrizeDto update(Long id, PrizeDto newDto);

    boolean deleteById(Long id);

    Set<PrizeDto> findAll();

    Set<PrizeDto> findByHackathonId(Long hackathonId);

    Set<PrizeDto> findByTeamId(Long teamId);
}