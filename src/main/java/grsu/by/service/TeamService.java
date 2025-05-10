package grsu.by.service;

import grsu.by.dto.teamDto.TeamCreationDto;
import grsu.by.dto.teamDto.TeamFullDto;

import java.util.List;

public interface TeamService {

    TeamCreationDto create(TeamCreationDto dto);

    TeamFullDto findById(Long id);

    TeamFullDto update(Long id, TeamFullDto newDto);

    boolean deleteById(Long id);

    List<TeamFullDto> findAll();

    TeamFullDto addMember(Long id, Long userId);

    TeamFullDto removeMember(Long id, Long userId);
}
