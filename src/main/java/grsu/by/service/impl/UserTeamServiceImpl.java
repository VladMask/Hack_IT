package grsu.by.service.impl;

import grsu.by.dto.UserTeamDto;
import grsu.by.entity.Team;
import grsu.by.entity.User;
import grsu.by.entity.UserTeam;
import grsu.by.entity.UserTeamId;
import grsu.by.repository.TeamRepository;
import grsu.by.repository.UserRepository;
import grsu.by.repository.UserTeamRepository;
import grsu.by.service.UserTeamService;
import grsu.by.util.ExceptionUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Transactional
public class UserTeamServiceImpl implements UserTeamService {

    private final UserTeamRepository userTeamRepository;
    private final UserRepository userRepository;
    private final TeamRepository teamRepository;
    private final ModelMapper mapper;

    @Override
    public UserTeamDto create(UserTeamDto dto) {
        UserTeam userTeam = mapper.map(dto, UserTeam.class);
        Team team = teamRepository.findById(dto.getTeamId()).orElseThrow(
                () -> ExceptionUtil.throwEntityNotFoundException(Team.class, dto.getTeamId().toString())
        );
        userTeam.setTeam(team);
        User user = userRepository.findById(dto.getUserId()).orElseThrow(
                () -> ExceptionUtil.throwEntityNotFoundException(User.class, dto.getUserId().toString())
        );
        userTeam.setUser(user);
        return mapper.map(userTeamRepository.save(userTeam), UserTeamDto.class);
    }

    @Override
    public UserTeamDto findById(Long id) {
        UserTeam userTeam = userTeamRepository.findById(new UserTeamId(id, id)).orElseThrow(
                () -> ExceptionUtil.throwEntityNotFoundException(UserTeam.class, id.toString())
        );
        return mapper.map(userTeam, UserTeamDto.class);
    }

    @Override
    public UserTeamDto update(Long id, UserTeamDto newDto) {
        UserTeam userTeam = userTeamRepository.findById(new UserTeamId(id, id)).orElseThrow(
                () -> ExceptionUtil.throwEntityNotFoundException(UserTeam.class, id.toString())
        );
        
        if (newDto.getIsLeader() != null) {
            userTeam.setIsLeader(newDto.getIsLeader());
        }
        
        return mapper.map(userTeamRepository.save(userTeam), UserTeamDto.class);
    }

    @Override
    public boolean deleteById(Long id) {
        userTeamRepository.deleteById(new UserTeamId(id, id));
        if (userTeamRepository.existsById(new UserTeamId(id, id))) {
            throw ExceptionUtil.throwEntityDeletionException(UserTeam.class, id.toString());
        }
        return true;
    }

    @Override
    public boolean isUserInTeam(Long userId, Long teamId) {
        return false;
    }

    @Override
    public boolean addUserToTeam(Long userId, Long teamId) {
        return false;
    }

    @Override
    public boolean removeUserFromTeam(Long userId, Long teamId) {
        return false;
    }
}
