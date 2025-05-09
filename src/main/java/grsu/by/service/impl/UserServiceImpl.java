package grsu.by.service.impl;

import grsu.by.dto.userDto.UserBaseDto;
import grsu.by.dto.userDto.UserCreationDto;
import grsu.by.entity.User;
import grsu.by.exception.EntityDeletionException;
import grsu.by.exception.EntityNotFoundException;
import grsu.by.repository.UserRepository;
import grsu.by.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;

@RequiredArgsConstructor
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;

    @Override
    public UserBaseDto create(UserCreationDto dto) {
        User user = mapper.map(dto, User.class);
        return mapper.map(userRepository.save(user), UserBaseDto.class);
    }

    @Override
    public UserBaseDto findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(
                        Map.of(
                                "class", User.class.getName(),
                                "id", id.toString(),
                                "date", Instant.now().toString()
                        )
                )
        );
        return mapper.map(user, UserBaseDto.class);
    }

    @Override
    public UserBaseDto update(Long id, UserBaseDto newDto) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(
                        Map.of(
                                "class", User.class.getName(),
                                "id", id.toString(),
                                "date", Instant.now().toString()
                        )
                )
        );
        if (newDto.getUsername() != null) {
            user.setUsername(newDto.getUsername());
        }
        if (newDto.getFirstname() != null && !StringUtils.EMPTY.equals(newDto.getFirstname())){
            user.setFirstname(newDto.getFirstname());
        }
        if (newDto.getLastname() != null && !StringUtils.EMPTY.equals(newDto.getLastname())){
            user.setLastname(newDto.getLastname());
        }

        return mapper.map(userRepository.save(user), UserBaseDto.class);
    }

    @Override
    public boolean deleteById(Long id) {
        userRepository.deleteById(id);
        if (userRepository.existsById(id)) {
            throw new EntityDeletionException(
                    Map.of(
                            "id", id.toString(),
                            "class", User.class.getName(),
                            "date", Instant.now().toString()
                    )
            );
        }
        return true;
    }

    @Override
    public boolean setUserRole(Long userId, String roleName) {
        return false;
    }
}
