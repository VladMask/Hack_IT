package grsu.by.service;

import grsu.by.dto.UserRoleDto;

public interface UserRoleService {

    UserRoleDto create(UserRoleDto dto);

    UserRoleDto findById(Long id);

    UserRoleDto update(Long id, UserRoleDto newDto);

    boolean deleteById(Long id);

    boolean addRoleToUser(Long userId, Long roleId);

    boolean removeRoleFromUser(Long userId, Long roleId);

    boolean hasRole(Long userId, Long roleId);
}
