package grsu.by.service.impl;

import grsu.by.dto.RoleDto;
import grsu.by.entity.Role;
import grsu.by.repository.RoleRepository;
import grsu.by.service.RoleService;
import grsu.by.util.ExceptionUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final ModelMapper mapper;

    @Override
    public RoleDto create(RoleDto dto) {
        Role role = mapper.map(dto, Role.class);
        return mapper.map(roleRepository.save(role), RoleDto.class);
    }

    @Override
    public RoleDto findById(Short id) {
        Role role = roleRepository.findById(id).orElseThrow(
                () -> ExceptionUtil.throwEntityNotFoundException(Role.class, id.toString())
        );
        return mapper.map(role, RoleDto.class);
    }

    @Override
    public RoleDto update(Short id, RoleDto newDto) {
        Role role = roleRepository.findById(id).orElseThrow(
                () -> ExceptionUtil.throwEntityNotFoundException(Role.class, id.toString())
        );
        if(newDto.getName() != null && !StringUtils.EMPTY.equals(newDto.getName()))
        {
            role.setName(newDto.getName());
        }
        return mapper.map(roleRepository.save(role), RoleDto.class);
    }

    @Override
    public boolean deleteById(Short id) {
        roleRepository.deleteById(id);
        if (roleRepository.existsById(id)) {
            throw ExceptionUtil.throwEntityDeletionException(Role.class, id.toString());
        }
        return true;
    }

    @Override
    public Set<RoleDto> findAll() {
        return roleRepository.findAll().stream()
                .map(role -> mapper.map(role, RoleDto.class))
                .collect(Collectors.toSet());
    }
}
