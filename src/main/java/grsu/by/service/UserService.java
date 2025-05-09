package grsu.by.service;

import grsu.by.dto.userDto.UserCreationDto;
import grsu.by.dto.userDto.UserBaseDto;

public interface UserService {

    UserBaseDto create(UserCreationDto dto);

    UserBaseDto findById(Long id);

    UserBaseDto update(Long id, UserBaseDto newDto);

    boolean deleteById(Long id);

    boolean setUserRole(Long userId, String roleName);

}
