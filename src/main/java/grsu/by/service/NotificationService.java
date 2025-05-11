package grsu.by.service;

import grsu.by.dto.NotificationDto;

import java.util.Set;

public interface NotificationService {

    NotificationDto create(NotificationDto dto);

    NotificationDto findById(Long id);

    NotificationDto update(Long id, NotificationDto newDto);

    boolean deleteById(Long id);

    Set<NotificationDto> findAll();

    Set<NotificationDto> findByUserId(Long userId);

    Set<NotificationDto> findByHackathonId(Long hackathonId);
}
