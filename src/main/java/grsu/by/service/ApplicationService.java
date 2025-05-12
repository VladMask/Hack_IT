package grsu.by.service;

import grsu.by.dto.applicationDto.ApplicationCreationDto;
import grsu.by.dto.applicationDto.ApplicationFullDto;

import java.util.Set;

public interface ApplicationService {

    ApplicationCreationDto create(ApplicationCreationDto dto);

    ApplicationFullDto findById(Long id);

    ApplicationFullDto update(Long id, ApplicationFullDto newDto);

    boolean deleteById(Long id);

    Set<ApplicationFullDto> findByTeamId(Long teamId);

    Set<ApplicationFullDto> findByHackathonId(Long hackathonId);

    boolean acceptApplication(Long id);

    boolean declineApplication(Long id);
}
