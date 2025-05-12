package grsu.by.service;

import grsu.by.dto.prizeDto.PrizeCreationDto;
import grsu.by.dto.prizeDto.PrizeFullDto;

import java.util.Set;

public interface PrizeService {

    PrizeCreationDto create(PrizeCreationDto dto);

    PrizeFullDto findById(Long id);

    PrizeFullDto update(Long id, PrizeFullDto newDto);

    boolean deleteById(Long id);

    Set<PrizeFullDto> findAll();

    Set<PrizeFullDto> findByHackathonId(Long hackathonId);

    Set<PrizeFullDto> findByTeamId(Long teamId);
}