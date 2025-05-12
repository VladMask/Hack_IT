package grsu.by.service.impl;

import grsu.by.dto.teamDto.TeamCreationDto;
import grsu.by.dto.teamDto.TeamFullDto;
import grsu.by.entity.Hackathon;
import grsu.by.entity.Team;
import grsu.by.entity.TeamHackathon;
import grsu.by.entity.User;
import grsu.by.entity.UserTeam;
import grsu.by.entity.UserTeamId;
import grsu.by.repository.HackathonRepository;
import grsu.by.repository.TeamHackathonRepository;
import grsu.by.repository.TeamRepository;
import grsu.by.repository.UserRepository;
import grsu.by.repository.UserTeamRepository;
import grsu.by.security.UserDetailsServiceImpl;
import grsu.by.service.TeamService;
import grsu.by.util.ExceptionUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final UserTeamRepository userTeamRepository;
    private final UserRepository userRepository;
    private final UserDetailsServiceImpl userDetailsService;
    private final TeamHackathonRepository teamHackathonRepository;
    private final HackathonRepository hackathonRepository;
    private final ModelMapper mapper;

    @Override
    public TeamCreationDto create(TeamCreationDto dto) {
        Team team = mapper.map(dto, Team.class);
        Team savedTeam = teamRepository.save(team);
        Long leaderId = userDetailsService.getUserIdFromSecurityContext();
        User leader = userRepository.findById(leaderId).orElseThrow(
                () -> ExceptionUtil.throwEntityNotFoundException(User.class, leaderId.toString())
        );
        UserTeamId userTeamId = UserTeamId.builder()
                .teamId(savedTeam.getId())
                .userId(leaderId)
                .build();
        UserTeam userTeam = UserTeam.builder()
                .id(userTeamId)
                .user(leader)
                .team(team)
                .isLeader(true)
                .build();
        userTeamRepository.save(userTeam);
        return mapper.map(savedTeam, TeamCreationDto.class);
    }

    @Override
    public TeamFullDto findById(Long id) {
        Team team = teamRepository.findById(id).orElseThrow(
                () -> ExceptionUtil.throwEntityNotFoundException(Team.class, id.toString())
        );
        return mapper.map(team, TeamFullDto.class);
    }

    @Override
    public TeamFullDto update(Long id, TeamFullDto newDto) {
        Team team = teamRepository.findById(id).orElseThrow(
                () -> ExceptionUtil.throwEntityNotFoundException(Team.class, id.toString())
        );
        
        if (newDto.getName() != null) {
            team.setName(newDto.getName());
        }
        if (newDto.getIsActive() != null) {
            team.setIsActive(newDto.getIsActive());
        }
        
        return mapper.map(teamRepository.save(team), TeamFullDto.class);
    }

    @Override
    public boolean deleteById(Long id) {
        teamRepository.deleteById(id);
        if (teamRepository.existsById(id)) {
            throw ExceptionUtil.throwEntityDeletionException(Team.class, id.toString());
        }
        return true;
    }

    @Override
    public Set<TeamCreationDto> findAll() {
        return teamRepository.findAll().stream()
                .map(team -> mapper.map(team, TeamCreationDto.class))
                .collect(Collectors.toSet());
    }

    @Override
    public boolean registerTeamForHackathon(Long teamId, Long hackathonId) {
        if (teamHackathonRepository.existsByTeamIdAndHackathonId(teamId, hackathonId)) {
            return false;
        }

        Team team = teamRepository.findById(teamId).orElseThrow(
                () -> ExceptionUtil.throwEntityNotFoundException(Team.class, teamId.toString())
        );

        Hackathon hackathon = hackathonRepository.findById(hackathonId).orElseThrow(
                () -> ExceptionUtil.throwEntityNotFoundException(Hackathon.class, hackathonId.toString())
        );

        TeamHackathon teamHackathon = new TeamHackathon();
        teamHackathon.setTeam(team);
        teamHackathon.setHackathon(hackathon);

        teamHackathonRepository.save(teamHackathon);
        return true;
    }

    @Override
    public boolean unregisterTeamFromHackathon(Long teamId, Long hackathonId) {
        Optional<TeamHackathon> record = teamHackathonRepository.findByTeamIdAndHackathonId(teamId, hackathonId);
        if (record.isPresent()) {
            teamHackathonRepository.delete(record.get());
            return true;
        }
        return false;
    }

    @Override
    public boolean isTeamRegisteredForHackathon(Long teamId, Long hackathonId) {
        return teamHackathonRepository.existsByTeamIdAndHackathonId(teamId, hackathonId);
    }
}
