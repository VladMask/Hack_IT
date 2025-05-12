package grsu.by.service.impl;

import grsu.by.dto.notificationDto.NotificationCreationDto;
import grsu.by.dto.notificationDto.NotificationFullDto;
import grsu.by.entity.Hackathon;
import grsu.by.entity.Notification;
import grsu.by.entity.User;
import grsu.by.repository.HackathonRepository;
import grsu.by.repository.NotificationRepository;
import grsu.by.repository.UserRepository;
import grsu.by.service.NotificationService;
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
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;
    private final HackathonRepository hackathonRepository;
    private final ModelMapper mapper;

    @Override
    public NotificationCreationDto create(NotificationCreationDto dto) {
        Notification notification = new Notification();
        notification.setTitle(dto.getTitle());
        notification.setContent(dto.getContent());

        User user = userRepository.findById(dto.getUserId()).orElseThrow(
                () -> ExceptionUtil.throwEntityNotFoundException(User.class, dto.getUserId().toString())
        );
        notification.setUser(user);

        Hackathon hackathon = hackathonRepository.findById(dto.getHackathonId()).orElseThrow(
                () -> ExceptionUtil.throwEntityNotFoundException(Hackathon.class, dto.getHackathonId().toString())
        );
        notification.setHackathon(hackathon);


        return mapper.map(notificationRepository.save(notification), NotificationCreationDto.class);
    }

    @Override
    public NotificationFullDto findById(Long id) {
        Notification notification = notificationRepository.findById(id).orElseThrow(
                () -> ExceptionUtil.throwEntityNotFoundException(Notification.class, id.toString())
        );
        return mapper.map(notification, NotificationFullDto.class);
    }

    @Override
    public NotificationFullDto update(Long id, NotificationFullDto newDto) {
        Notification notification = notificationRepository.findById(id).orElseThrow(
                () -> ExceptionUtil.throwEntityNotFoundException(Notification.class, id.toString())
        );

        if (newDto.getTitle() != null) {
            notification.setTitle(newDto.getTitle());
        }
        if (newDto.getContent() != null) {
            notification.setContent(newDto.getContent());
        }

        return mapper.map(notificationRepository.save(notification), NotificationFullDto.class);
    }

    @Override
    public boolean deleteById(Long id) {
        notificationRepository.deleteById(id);
        if (notificationRepository.existsById(id)) {
            throw ExceptionUtil.throwEntityDeletionException(Notification.class, id.toString());
        }
        return true;
    }

    @Override
    public Set<NotificationFullDto> findAll() {
        return notificationRepository.findAll().stream()
                .map(notification -> mapper.map(notification, NotificationFullDto.class))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<NotificationFullDto> findByUserId(Long userId) {
        Set<Notification> notifications = notificationRepository.findAllByUserId(userId);
        return notifications.stream()
                .map(notification -> mapper.map(notification, NotificationFullDto.class))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<NotificationFullDto> findByHackathonId(Long hackathonId) {
        Set<Notification> notifications = notificationRepository.findAllByHackathonId(hackathonId);
        return notifications.stream()
                .map(notification -> mapper.map(notification, NotificationFullDto.class))
                .collect(Collectors.toSet());
    }
}