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
    public UserTeamDto findById(UserTeamId id) {
        UserTeam userTeam = userTeamRepository.findById(id).orElseThrow(
                () -> ExceptionUtil.throwEntityNotFoundException(UserTeam.class, id.toString())
        );
        return mapper.map(userTeam, UserTeamDto.class);
    }

    @Override
    public UserTeamDto update(UserTeamId id, UserTeamDto newDto) {
        UserTeam userTeam = userTeamRepository.findById(id).orElseThrow(
                () -> ExceptionUtil.throwEntityNotFoundException(UserTeam.class, id.toString())
        );
        
        if (newDto.getIsLeader() != null) {
            userTeam.setIsLeader(newDto.getIsLeader());
        }
        
        return mapper.map(userTeamRepository.save(userTeam), UserTeamDto.class);
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
}
