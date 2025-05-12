package grsu.by.service.impl;

import grsu.by.dto.ApplicationDto;
import grsu.by.entity.Application;
import grsu.by.entity.Hackathon;
import grsu.by.entity.Team;
import grsu.by.entity.TeamHackathon;
import grsu.by.repository.ApplicationRepository;
import grsu.by.repository.HackathonRepository;
import grsu.by.repository.TeamHackathonRepository;
import grsu.by.repository.TeamRepository;
import grsu.by.service.ApplicationService;
import grsu.by.util.ExceptionUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final TeamRepository teamRepository;
    private final HackathonRepository hackathonRepository;
    private final ModelMapper mapper;
    private final TeamHackathonRepository teamHackathonRepository;

    @Override
    public ApplicationDto create(ApplicationDto dto) {
        Team team = teamRepository.findById(dto.getTeamId()).orElseThrow(
                () -> ExceptionUtil.throwEntityNotFoundException(Team.class, dto.getTeamId().toString())
        );
        Hackathon hackathon = hackathonRepository.findById(dto.getHackathonId()).orElseThrow(
                () -> ExceptionUtil.throwEntityNotFoundException(Hackathon.class, dto.getHackathonId().toString())
        );

        if (applicationRepository.existsByTeamIdAndHackathonId(dto.getTeamId(), dto.getHackathonId())) {
            throw new IllegalStateException("Team already has an application for this hackathon");
        }

        if (hackathon.getRegistrationEnd().isBefore(java.time.OffsetDateTime.now())) {
            throw new IllegalStateException("Hackathon registration is closed");
        }
        
        Application application = mapper.map(dto, Application.class);
        application.setTeam(team);
        application.setHackathon(hackathon);
        application.setIsAccepted(false);

        return mapper.map(applicationRepository.save(application), ApplicationDto.class);
    }

    @Override
    public ApplicationDto findById(Long id) {
        Application application = applicationRepository.findById(id).orElseThrow(
                () -> ExceptionUtil.throwEntityNotFoundException(Application.class, id.toString())
        );
        return mapper.map(application, ApplicationDto.class);
    }

    @Override
    public ApplicationDto update(Long id, ApplicationDto newDto) {
        Application application = applicationRepository.findById(id).orElseThrow(
                () -> ExceptionUtil.throwEntityNotFoundException(Application.class, id.toString())
        );

        if (newDto.getIsAccepted() != null) {
            application.setIsAccepted(newDto.getIsAccepted());
        }
        
        if (newDto.getProjectDescription() != null && !StringUtils.EMPTY.equals(newDto.getProjectDescription())){
            application.setProjectDescription(newDto.getProjectDescription());
        }

        return mapper.map(applicationRepository.save(application), ApplicationDto.class);
    }

    @Override
    public boolean deleteById(Long id) {
        applicationRepository.deleteById(id);
        if (applicationRepository.existsById(id)) {
            throw ExceptionUtil.throwEntityDeletionException(Application.class, id.toString());
        }
        return true;
    }

    @Override
    public Set<ApplicationDto> findByTeamId(Long teamId) {
        Set<Application> applications = applicationRepository.findAllByTeamId(teamId);
        return applications.stream()
                .map(app -> mapper.map(app, ApplicationDto.class))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<ApplicationDto> findByHackathonId(Long hackathonId) {
        Set<Application> applications = applicationRepository.findAllByHackathonId(hackathonId);
        return applications.stream()
                .map(app -> mapper.map(app, ApplicationDto.class))
                .collect(Collectors.toSet());
    }

    @Override
    public boolean acceptApplication(Long id) {
        Application application = applicationRepository.findByIdWithDetails(id).orElseThrow(
                () -> ExceptionUtil.throwEntityNotFoundException(Application.class, id.toString())
        );
        application.setIsAccepted(true);
        return registerTeamForHackathon(application.getTeam(), application.getHackathon());
    }

    private boolean registerTeamForHackathon(Team team, Hackathon hackathon) {
        if (teamHackathonRepository.existsByTeamIdAndHackathonId(team.getId(), hackathon.getId())) {
            return false;
        }

        TeamHackathon teamHackathon = new TeamHackathon();
        teamHackathon.setTeam(team);
        teamHackathon.setHackathon(hackathon);

        teamHackathonRepository.save(teamHackathon);
        return true;
    }
}

