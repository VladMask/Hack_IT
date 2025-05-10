package grsu.by.service;

import grsu.by.dto.ApplicationDto;

public interface ApplicationService {

    ApplicationDto create(ApplicationDto dto);

    ApplicationDto findById(Long id);

    ApplicationDto update(Long id, ApplicationDto newDto);

    boolean deleteById(Long id);

}
