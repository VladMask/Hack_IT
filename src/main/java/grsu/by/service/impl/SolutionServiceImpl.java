package grsu.by.service.impl;

import grsu.by.dto.solutionDto.SolutionCreationDto;
import grsu.by.dto.solutionDto.SolutionFullDto;
import grsu.by.dto.solutionDto.SolutionShortDto;
import grsu.by.entity.Hackathon;
import grsu.by.entity.Solution;
import grsu.by.entity.Team;
import grsu.by.repository.HackathonRepository;
import grsu.by.repository.SolutionRepository;
import grsu.by.repository.TeamRepository;
import grsu.by.service.SolutionService;
import grsu.by.util.ExceptionUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class SolutionServiceImpl implements SolutionService {

    private final SolutionRepository solutionRepository;
    private final HackathonRepository hackathonRepository;
    private final TeamRepository teamRepository;
    private final ModelMapper mapper;

    @Override
    public SolutionCreationDto create(SolutionCreationDto dto) {
        Solution solution = mapper.map(dto, Solution.class);
        solution.setTotalScore(0);
        Hackathon hackathon = hackathonRepository.findById(dto.getHackathonId()).orElseThrow(
                () -> ExceptionUtil.throwEntityNotFoundException(Hackathon.class, dto.getHackathonId().toString())
        );
        solution.setHackathon(hackathon);
        Team team = teamRepository.findById(dto.getTeamId()).orElseThrow(
                () -> ExceptionUtil.throwEntityNotFoundException(Team.class, dto.getTeamId().toString())
        );
        solution.setTeam(team);
        return mapper.map(solutionRepository.save(solution), SolutionCreationDto.class);
    }

    @Override
    public SolutionFullDto findById(Long id) {
        Solution solution = solutionRepository.findById(id).orElseThrow(
                () -> ExceptionUtil.throwEntityNotFoundException(Solution.class, id.toString())
        );
        return mapper.map(solution, SolutionFullDto.class);
    }

    @Override
    public SolutionFullDto update(Long id, SolutionFullDto newDto) {
        Solution solution = solutionRepository.findById(id).orElseThrow(
                () -> ExceptionUtil.throwEntityNotFoundException(Solution.class, id.toString())
        );
        
        if (newDto.getIsGitRepository() != null) {
            solution.setIsGitRepository(newDto.getIsGitRepository());
        }
        if (newDto.getRepositoryUrl() != null) {
            solution.setRepositoryUrl(newDto.getRepositoryUrl());
        }
        if (newDto.getFilesUrl() != null) {
            solution.setFilesUrl(newDto.getFilesUrl());
        }
        if (newDto.getTotalScore() != null) {
            solution.setTotalScore(newDto.getTotalScore());
        }
        if (newDto.getCheckedAt() != null) {
            solution.setCheckedAt(newDto.getCheckedAt());
        }
        
        return mapper.map(solutionRepository.save(solution), SolutionFullDto.class);
    }

    @Override
    public boolean deleteById(Long id) {
        solutionRepository.deleteById(id);
        if (solutionRepository.existsById(id)) {
            throw ExceptionUtil.throwEntityDeletionException(Solution.class, id.toString());
        }
        return true;
    }

    @Override
    public Set<SolutionShortDto> findAll() {
        return solutionRepository.findAll().stream()
                .map(solution -> mapper.map(solution, SolutionShortDto.class))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<SolutionShortDto> findByTeamId(Long teamId) {
        return solutionRepository.findByTeamId(teamId).stream()
                .map(solution -> mapper.map(solution, SolutionShortDto.class))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<SolutionShortDto> findByHackathonId(Long hackathonId) {
        return solutionRepository.findByHackathonId(hackathonId).stream()
                .map(solution -> mapper.map(solution, SolutionShortDto.class))
                .collect(Collectors.toSet());
    }
}
