package grsu.by.service;

import grsu.by.dto.ApplicationDto;

import java.util.Set;

public interface ApplicationService {

    ApplicationDto create(ApplicationDto dto);

    ApplicationDto findById(Long id);

    ApplicationDto update(Long id, ApplicationDto newDto);

    boolean deleteById(Long id);

    Set<ApplicationDto> findByTeamId(Long teamId);

    Set<ApplicationDto> findByHackathonId(Long hackathonId);
}
