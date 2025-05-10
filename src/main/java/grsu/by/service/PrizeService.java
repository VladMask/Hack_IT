package grsu.by.service;

import grsu.by.dto.PrizeDto;

public interface PrizeService {

    PrizeDto create(PrizeDto dto);

    PrizeDto findById(Long id);

    PrizeDto update(Long id, PrizeDto newDto);

    boolean deleteById(Long id);

}