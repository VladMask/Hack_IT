package grsu.by.service;

import grsu.by.dto.UserRoleDto;

public interface UserRoleService {

    UserRoleDto create(UserRoleDto dto);

    UserRoleDto findById(Long id);

    UserRoleDto update(Long id, UserRoleDto newDto);

    boolean deleteById(Long id);

    boolean addRoleToUser(Long userId, String roleName);

    boolean removeRoleFromUser(Long userId, String roleName);

    boolean hasRole(Long userId, String roleName);
}
