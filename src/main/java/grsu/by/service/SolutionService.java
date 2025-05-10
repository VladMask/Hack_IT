package grsu.by.service;

import grsu.by.dto.solutionDto.SolutionCreationDto;
import grsu.by.dto.solutionDto.SolutionFullDto;

public interface SolutionService {

    SolutionCreationDto create(SolutionCreationDto dto);

    SolutionFullDto findById(Long id);

    SolutionFullDto update(Long id, SolutionFullDto newDto);

    boolean deleteById(Long id);

}
