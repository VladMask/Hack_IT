package grsu.by.service.impl;

import grsu.by.dto.teamDto.TeamCreationDto;
import grsu.by.dto.teamDto.TeamFullDto;
import grsu.by.entity.Team;
import grsu.by.repository.TeamRepository;
import grsu.by.service.TeamService;
import grsu.by.util.ExceptionUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final ModelMapper mapper;

    @Override
    public TeamCreationDto create(TeamCreationDto dto) {
        Team team = mapper.map(dto, Team.class);
        team.setIsActive(true);
        return mapper.map(teamRepository.save(team), TeamCreationDto.class);
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
    public List<TeamFullDto> findAll() {
        return List.of();
    }

    @Override
    public TeamFullDto addMember(Long id, Long userId) {
        return null;
    }

    @Override
    public TeamFullDto removeMember(Long id, Long userId) {
        return null;
    }
}
