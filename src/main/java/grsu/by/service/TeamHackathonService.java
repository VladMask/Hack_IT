package grsu.by.service;

import grsu.by.dto.TeamHackathonDto;

public interface TeamHackathonService {

    TeamHackathonDto create(TeamHackathonDto dto);

    TeamHackathonDto findById(Long id);

    TeamHackathonDto update(Long id, TeamHackathonDto newDto);

    boolean deleteById(Long id);

}
