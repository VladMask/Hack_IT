package grsu.by.service;

import grsu.by.dto.solutionDto.SolutionCreationDto;
import grsu.by.dto.solutionDto.SolutionFullDto;
import grsu.by.dto.solutionDto.SolutionShortDto;

import java.util.Set;

public interface SolutionService {

    SolutionCreationDto create(SolutionCreationDto dto);

    SolutionFullDto findById(Long id);

    SolutionFullDto update(Long id, SolutionFullDto newDto);

    boolean deleteById(Long id);

    Set<SolutionShortDto> findAll();

    Set<SolutionShortDto> findByTeamId(Long teamId);

    Set<SolutionShortDto> findByHackathonId(Long hackathonId);
}
