package grsu.by.service;

import grsu.by.dto.UserTeamDto;
import grsu.by.entity.UserTeamId;

public interface UserTeamService {

    UserTeamDto findById(UserTeamId id);

    UserTeamDto update(UserTeamId id, UserTeamDto newDto);

    boolean isUserInTeam(UserTeamId id);

    boolean addUserToTeam(UserTeamId id);

    boolean removeUserFromTeam(UserTeamId id);
}
