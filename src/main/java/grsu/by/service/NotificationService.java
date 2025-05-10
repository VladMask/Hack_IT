package grsu.by.service;

import grsu.by.dto.NotificationDto;

public interface NotificationService {

    NotificationDto create(NotificationDto dto);

    NotificationDto findById(Long id);

    NotificationDto update(Long id, NotificationDto newDto);

    boolean deleteById(Long id);

}
