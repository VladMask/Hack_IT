package grsu.by.service;

import grsu.by.dto.userDto.UserCreationDto;
import grsu.by.dto.userDto.UserBaseDto;
import grsu.by.entity.UserTeamId;

public interface UserService {

    UserBaseDto create(UserCreationDto dto);

    UserBaseDto findById(Long id);

    UserBaseDto update(Long id, UserBaseDto newDto);

    boolean deleteById(Long id);

    boolean addRoleToUser(Long userId, String roleName);

    boolean removeRoleFromUser(Long userId, String roleName);

    boolean hasRole(Long userId, String roleName);

    boolean isUserInTeam(UserTeamId id);

    boolean addUserToTeam(UserTeamId id);

    boolean removeUserFromTeam(UserTeamId id);

}
