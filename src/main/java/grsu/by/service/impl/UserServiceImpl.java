package grsu.by.service.impl;

import grsu.by.dto.userDto.UserBaseDto;
import grsu.by.dto.userDto.UserCreationDto;
import grsu.by.entity.Role;
import grsu.by.entity.User;
import grsu.by.entity.UserRole;
import grsu.by.repository.RoleRepository;
import grsu.by.repository.UserRepository;
import grsu.by.repository.UserRoleRepository;
import grsu.by.service.UserService;
import grsu.by.util.ExceptionUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
    private final ModelMapper mapper;

    @Override
    public UserBaseDto create(UserCreationDto dto) {
        User user = mapper.map(dto, User.class);
        return mapper.map(userRepository.save(user), UserBaseDto.class);
    }

    @Override
    public UserBaseDto findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> ExceptionUtil.throwEntityNotFoundException(User.class, id.toString())
        );
        return mapper.map(user, UserBaseDto.class);
    }

    @Override
    public UserBaseDto update(Long id, UserBaseDto newDto) {
        User user = userRepository.findById(id).orElseThrow(
                () -> ExceptionUtil.throwEntityNotFoundException(User.class, id.toString())
        );
        if (newDto.getUsername() != null) {
            user.setUsername(newDto.getUsername());
        }
        if (newDto.getFirstname() != null && !StringUtils.EMPTY.equals(newDto.getFirstname())){
            user.setFirstname(newDto.getFirstname());
        }
        if (newDto.getLastname() != null && !StringUtils.EMPTY.equals(newDto.getLastname())){
            user.setLastname(newDto.getLastname());
        }

        return mapper.map(userRepository.save(user), UserBaseDto.class);
    }

    @Override
    public boolean deleteById(Long id) {
        userRepository.deleteById(id);
        if (userRepository.existsById(id)) {
            throw ExceptionUtil.throwEntityDeletionException(User.class, id.toString());
        }
        return true;
    }

    @Override
    public boolean setUserRole(Long userId, String roleName) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> ExceptionUtil.throwEntityNotFoundException(User.class, userId.toString())
        );
        
        Role role = roleRepository.findByName(roleName).orElseThrow(
                () -> ExceptionUtil.throwEntityNotFoundException(Role.class, roleName)
        );
        
        // Проверяем, есть ли уже такая роль у пользователя
        if (user.getUserRoles().stream().anyMatch(ur -> ur.getRole().getName().equals(roleName))) {
            return false;
        }
        
        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);
        userRoleRepository.save(userRole);
        
        return true;
    }
}
