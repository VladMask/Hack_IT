package grsu.by.service.impl;

import grsu.by.dto.TeamHackathonDto;
import grsu.by.entity.Hackathon;
import grsu.by.entity.Team;
import grsu.by.entity.TeamHackathon;
import grsu.by.repository.HackathonRepository;
import grsu.by.repository.TeamHackathonRepository;
import grsu.by.repository.TeamRepository;
import grsu.by.service.TeamHackathonService;
import grsu.by.util.ExceptionUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Transactional
public class TeamHackathonServiceImpl implements TeamHackathonService {

    private final TeamHackathonRepository teamHackathonRepository;
    private final HackathonRepository hackathonRepository;
    private final TeamRepository teamRepository;
    private final ModelMapper mapper;

    @Override
    public TeamHackathonDto create(TeamHackathonDto dto) {
        TeamHackathon teamHackathon = mapper.map(dto, TeamHackathon.class);
        Hackathon hackathon = hackathonRepository.findById(dto.getHackathonId()).orElseThrow(
                () -> ExceptionUtil.throwEntityNotFoundException(Hackathon.class, dto.getHackathonId().toString())
        );
        teamHackathon.setHackathon(hackathon);
        Team team = teamRepository.findById(dto.getTeamId()).orElseThrow(
                () -> ExceptionUtil.throwEntityNotFoundException(Team.class, dto.getTeamId().toString())
        );
        teamHackathon.setTeam(team);
        return mapper.map(teamHackathonRepository.save(teamHackathon), TeamHackathonDto.class);
    }

    @Override
    public TeamHackathonDto findById(Long id) {
        TeamHackathon teamHackathon = teamHackathonRepository.findById(id).orElseThrow(
                () -> ExceptionUtil.throwEntityNotFoundException(TeamHackathon.class, id.toString())
        );
        return mapper.map(teamHackathon, TeamHackathonDto.class);
    }

    @Override
    public TeamHackathonDto update(Long id, TeamHackathonDto newDto) {
        TeamHackathon teamHackathon = teamHackathonRepository.findById(id).orElseThrow(
                () -> ExceptionUtil.throwEntityNotFoundException(TeamHackathon.class, id.toString())
        );
        
        if (newDto.getIsWinner() != null) {
            teamHackathon.setIsWinner(newDto.getIsWinner());
        }
        if (newDto.getPlace() != null) {
            teamHackathon.setPlace(newDto.getPlace());
        }
        
        return mapper.map(teamHackathonRepository.save(teamHackathon), TeamHackathonDto.class);
    }

    @Override
    public boolean deleteById(Long id) {
        teamHackathonRepository.deleteById(id);
        if (teamHackathonRepository.existsById(id)) {
            throw ExceptionUtil.throwEntityDeletionException(TeamHackathon.class, id.toString());
        }
        return true;
    }

    @Override
    public boolean registerTeamForHackathon(Long teamId, Long hackathonId) {
        return false;
    }

    @Override
    public boolean unregisterTeamFromHackathon(Long teamId, Long hackathonId) {
        return false;
    }

    @Override
    public boolean isTeamRegisteredForHackathon(Long teamId, Long hackathonId) {
        return false;
    }
}
