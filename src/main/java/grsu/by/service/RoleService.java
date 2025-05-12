package grsu.by.service;

import grsu.by.dto.RoleDto;

import java.util.Set;

public interface RoleService {

    RoleDto create(RoleDto dto);

    RoleDto findById(Short id);

    RoleDto update(Short id, RoleDto newDto);

    boolean deleteById(Short id);

    Set<RoleDto> findAll();
}