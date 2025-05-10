package grsu.by.service;

import grsu.by.dto.solutionDto.SolutionCreationDto;
import grsu.by.dto.solutionDto.SolutionFullDto;
import grsu.by.dto.solutionDto.SolutionShortDto;

import java.util.List;

public interface SolutionService {

    SolutionCreationDto create(SolutionCreationDto dto);

    SolutionFullDto findById(Long id);

    SolutionFullDto update(Long id, SolutionFullDto newDto);

    boolean deleteById(Long id);

    List<SolutionShortDto> findAll();

    List<SolutionShortDto> findByTeamId(Long teamId);

    List<SolutionShortDto> findByHackathonId(Long hackathonId);
}
