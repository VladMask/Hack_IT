package grsu.by.service;

import grsu.by.dto.ApplicationDto;

import java.util.List;

public interface ApplicationService {

    ApplicationDto create(ApplicationDto dto);

    ApplicationDto findById(Long id);

    ApplicationDto update(Long id, ApplicationDto newDto);

    boolean deleteById(Long id);

    List<ApplicationDto> findByTeamId(Long teamId);

    List<ApplicationDto> findByHackathonId(Long hackathonId);
}
