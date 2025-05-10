package grsu.by.service;

import grsu.by.dto.hackathonDto.HackathonCreationDto;
import grsu.by.dto.hackathonDto.HackathonFullDto;

public interface HackathonService {

    HackathonCreationDto create(HackathonCreationDto dto);

    HackathonFullDto findById(Long id);

    HackathonFullDto update(Long id, HackathonFullDto newDto);

    boolean deleteById(Long id);

    boolean addJudge(Long hackathonId, Long userId);

    boolean removeJudge(Long hackathonId, Long userId);

    boolean finishHackathon(Long id);

}
