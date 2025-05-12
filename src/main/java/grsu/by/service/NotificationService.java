package grsu.by.service;

import grsu.by.dto.notificationDto.NotificationCreationDto;
import grsu.by.dto.notificationDto.NotificationFullDto;

import java.util.Set;

public interface NotificationService {

    NotificationCreationDto create(NotificationCreationDto dto);

    NotificationFullDto findById(Long id);

    NotificationFullDto update(Long id, NotificationFullDto newDto);

    boolean deleteById(Long id);

    Set<NotificationFullDto> findAll();

    Set<NotificationFullDto> findByUserId(Long userId);

    Set<NotificationFullDto> findByHackathonId(Long hackathonId);
}
