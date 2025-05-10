package grsu.by.service.impl;

import grsu.by.dto.NotificationDto;
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

@RequiredArgsConstructor
@Service
@Transactional
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;
    private final HackathonRepository hackathonRepository;
    private final ModelMapper mapper;

    @Override
    public NotificationDto create(NotificationDto dto) {
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


        return mapper.map(notificationRepository.save(notification), NotificationDto.class);
    }

    @Override
    public NotificationDto findById(Long id) {
        Notification notification = notificationRepository.findById(id).orElseThrow(
                () -> ExceptionUtil.throwEntityNotFoundException(Notification.class, id.toString())
        );
        return mapper.map(notification, NotificationDto.class);
    }

    @Override
    public NotificationDto update(Long id, NotificationDto newDto) {
        Notification notification = notificationRepository.findById(id).orElseThrow(
                () -> ExceptionUtil.throwEntityNotFoundException(Notification.class, id.toString())
        );

        if (newDto.getTitle() != null) {
            notification.setTitle(newDto.getTitle());
        }
        if (newDto.getContent() != null) {
            notification.setContent(newDto.getContent());
        }
        if (newDto.getUserId() != null) {
            User user = userRepository.findById(newDto.getUserId()).orElseThrow(
                    () -> ExceptionUtil.throwEntityNotFoundException(User.class, newDto.getUserId().toString())
            );
            notification.setUser(user);
        }
        if (newDto.getHackathonId() != null) {
            Hackathon hackathon = hackathonRepository.findById(newDto.getHackathonId()).orElseThrow(
                    () -> ExceptionUtil.throwEntityNotFoundException(Hackathon.class, newDto.getHackathonId().toString())
            );
            notification.setHackathon(hackathon);
        }

        return mapper.map(notificationRepository.save(notification), NotificationDto.class);
    }

    @Override
    public boolean deleteById(Long id) {
        notificationRepository.deleteById(id);
        if (notificationRepository.existsById(id)) {
            throw ExceptionUtil.throwEntityDeletionException(Notification.class, id.toString());
        }
        return true;
    }
}