package grsu.by.service;

import grsu.by.dto.NotificationDto;

import java.util.List;

public interface NotificationService {

    NotificationDto create(NotificationDto dto);

    NotificationDto findById(Long id);

    NotificationDto update(Long id, NotificationDto newDto);

    boolean deleteById(Long id);

    List<NotificationDto> findAll();

    List<NotificationDto> findByUserId(Long userId);

    List<NotificationDto> findByHackathonId(Long hackathonId);
}
