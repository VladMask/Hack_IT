package grsu.by.service.impl;

import grsu.by.dto.UserRoleDto;
import grsu.by.entity.Role;
import grsu.by.entity.User;
import grsu.by.entity.UserRole;
import grsu.by.repository.RoleRepository;
import grsu.by.repository.UserRepository;
import grsu.by.repository.UserRoleRepository;
import grsu.by.service.UserRoleService;
import grsu.by.util.ExceptionUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Transactional
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper mapper;

    @Override
    public UserRoleDto create(UserRoleDto dto) {
        UserRole userRole = new UserRole();
        User user = userRepository.findById(dto.getUserId()).orElseThrow(
                () -> ExceptionUtil.throwEntityNotFoundException(User.class, dto.getUserId().toString())
        );
        Role role = roleRepository.findById(dto.getRoleId().shortValue()).orElseThrow(
                () -> ExceptionUtil.throwEntityNotFoundException(Role.class, dto.getRoleId().toString())
        );
        userRole.setUser(user);
        userRole.setRole(role);
        return mapper.map(userRoleRepository.save(userRole), UserRoleDto.class);
    }

    @Override
    public UserRoleDto findById(Long id) {
        UserRole userRole = userRoleRepository.findById(id).orElseThrow(
                () -> ExceptionUtil.throwEntityNotFoundException(UserRole.class, id.toString())
        );
        return mapper.map(userRole, UserRoleDto.class);
    }

    @Override
    public UserRoleDto update(Long id, UserRoleDto newDto) {
        UserRole userRole = userRoleRepository.findById(id).orElseThrow(
                () -> ExceptionUtil.throwEntityNotFoundException(UserRole.class, id.toString())
        );
        
        if (newDto.getRoleId() != null) {
            Role role = roleRepository.findById(newDto.getRoleId().shortValue()).orElseThrow(
                    () -> ExceptionUtil.throwEntityNotFoundException(Role.class, newDto.getRoleId().toString())
            );
            userRole.setRole(role);
        }
        if (newDto.getUserId() != null) {
            User user = userRepository.findById(newDto.getUserId()).orElseThrow(
                    () -> ExceptionUtil.throwEntityNotFoundException(User.class, newDto.getUserId().toString())
            );
            userRole.setUser(user);
        }
        
        return mapper.map(userRoleRepository.save(userRole), UserRoleDto.class);
    }

    @Override
    public boolean deleteById(Long id) {
        userRoleRepository.deleteById(id);
        if (userRoleRepository.existsById(id)) {
            throw ExceptionUtil.throwEntityDeletionException(UserRole.class, id.toString());
        }
        return true;
    }
}
