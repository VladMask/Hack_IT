package grsu.by.service;

import grsu.by.dto.teamDto.TeamCreationDto;
import grsu.by.dto.teamDto.TeamFullDto;

import java.util.Set;

public interface TeamService {

    TeamCreationDto create(TeamCreationDto dto);

    TeamFullDto findById(Long id);

    TeamFullDto update(Long id, TeamFullDto newDto);

    boolean deleteById(Long id);

    Set<TeamFullDto> findAll();
}
