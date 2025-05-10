package grsu.by.service;

import grsu.by.dto.UserTeamDto;

public interface UserTeamService {

    UserTeamDto create(UserTeamDto dto);

    UserTeamDto findById(Long id);

    UserTeamDto update(Long id, UserTeamDto newDto);

    boolean deleteById(Long id);

    boolean isUserInTeam(Long userId, Long teamId);

    boolean addUserToTeam(Long userId, Long teamId);

    boolean removeUserFromTeam(Long userId, Long teamId);
}
