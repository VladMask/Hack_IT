package grsu.by.service.impl;

import grsu.by.dto.userDto.UserBaseDto;
import grsu.by.dto.userDto.UserCreationDto;
import grsu.by.entity.Role;
import grsu.by.entity.Team;
import grsu.by.entity.User;
import grsu.by.entity.UserRole;
import grsu.by.entity.UserTeam;
import grsu.by.entity.UserTeamId;
import grsu.by.exception.EntityNotFoundException;
import grsu.by.repository.RoleRepository;
import grsu.by.repository.TeamRepository;
import grsu.by.repository.UserRepository;
import grsu.by.repository.UserRoleRepository;
import grsu.by.repository.UserTeamRepository;
import grsu.by.service.UserService;
import grsu.by.util.ExceptionUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;

@RequiredArgsConstructor
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
    private final UserTeamRepository userTeamRepository;
    private final TeamRepository teamRepository;
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


        if (user.getUserRoles().stream().anyMatch(ur -> ur.getRole().getName().equals(roleName))) {
            return false;
        }
        
        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);
        userRoleRepository.save(userRole);
        
        return true;
    }

    @Override
    public boolean isUserInTeam(UserTeamId id) {
        return userTeamRepository.existsById(id);
    }

    @Override
    public boolean addUserToTeam(UserTeamId id) {
        UserTeam userTeam = new UserTeam();
        Team team = teamRepository.findById(id.getTeamId()).orElseThrow(
                () -> ExceptionUtil.throwEntityNotFoundException(Team.class, id.getTeamId().toString())
        );
        userTeam.setTeam(team);
        User user = userRepository.findById(id.getUserId()).orElseThrow(
                () -> ExceptionUtil.throwEntityNotFoundException(User.class, id.getUserId().toString())
        );
        userTeam.setUser(user);
        userTeam.setId(id);
        userTeamRepository.save(userTeam);
        return userTeamRepository.existsById(id);
    }

    @Override
    public boolean removeUserFromTeam(UserTeamId id) {
        userTeamRepository.deleteById(id);
        if (userTeamRepository.existsById(id)) {
            throw ExceptionUtil.throwEntityDeletionException(UserTeam.class, id.toString());
        }
        return true;
    }

    @Override
    public boolean addRoleToUser(Long userId, String roleName) {
        if (userRoleRepository.existsByUserIdAndRoleName(userId, roleName)) {
            return false;
        }

        UserRole userRole = new UserRole();
        User user = userRepository.findById(userId).orElseThrow(
                () -> ExceptionUtil.throwEntityNotFoundException(User.class, userId.toString())
        );
        Role role = roleRepository.findByName(roleName).orElseThrow(
                () -> new EntityNotFoundException(
                        Map.of(
                                "class", Role.class.getName(),
                                "name", roleName,
                                "date", Instant.now().toString()
                        )
                )
        );
        userRole.setUser(user);
        userRole.setRole(role);
        userRole = userRoleRepository.save(userRole);
        return userRoleRepository.existsById(userRole.getId());
    }

    @Override
    public boolean removeRoleFromUser(Long userId, String roleName) {
        if (userRoleRepository.existsByUserIdAndRoleName(userId, roleName)) {
            userRoleRepository.deleteByUserIdAndRoleName(userId, roleName);
            return true;
        }
        return false;
    }

    @Override
    public boolean hasRole(Long userId, String roleName) {
        return userRoleRepository.existsByUserIdAndRoleName(userId, roleName);
    }
}
