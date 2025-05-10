package grsu.by.service;

import grsu.by.dto.UserTeamDto;

public interface UserTeamService {

    UserTeamDto create(UserTeamDto dto);

    UserTeamDto findById(Long id);

    UserTeamDto update(Long id, UserTeamDto newDto);

    boolean deleteById(Long id);

}
