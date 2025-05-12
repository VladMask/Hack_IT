package grsu.by.service.impl;

import grsu.by.dto.prizeDto.PrizeCreationDto;
import grsu.by.dto.prizeDto.PrizeFullDto;
import grsu.by.entity.Hackathon;
import grsu.by.entity.Prize;
import grsu.by.repository.HackathonRepository;
import grsu.by.repository.PrizeRepository;
import grsu.by.service.PrizeService;
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
public class PrizeServiceImpl implements PrizeService {

    private final PrizeRepository prizeRepository;
    private final HackathonRepository hackathonRepository;
    private final ModelMapper mapper;

    @Override
    public PrizeCreationDto create(PrizeCreationDto dto) {
        Prize prize = mapper.map(dto, Prize.class);
        Hackathon hackathon = hackathonRepository.findById(dto.getHackathonId()).orElseThrow(
                () -> ExceptionUtil.throwEntityNotFoundException(Hackathon.class, dto.getHackathonId().toString())
        );
        prize.setHackathon(hackathon);
        return mapper.map(prizeRepository.save(prize), PrizeCreationDto.class);
    }

    @Override
    public PrizeFullDto findById(Long id) {
        Prize prize = prizeRepository.findById(id).orElseThrow(
                () -> ExceptionUtil.throwEntityNotFoundException(Prize.class, id.toString())
        );
        return mapper.map(prize, PrizeFullDto.class);
    }

    @Override
    public PrizeFullDto update(Long id, PrizeFullDto newDto) {
        Prize prize = prizeRepository.findById(id).orElseThrow(
                () -> ExceptionUtil.throwEntityNotFoundException(Prize.class, id.toString())
        );
        
        if (newDto.getName() != null) {
            prize.setName(newDto.getName());
        }
        if (newDto.getQuantity() != null) {
            prize.setQuantity(newDto.getQuantity());
        }
        
        return mapper.map(prizeRepository.save(prize), PrizeFullDto.class);
    }

    @Override
    public boolean deleteById(Long id) {
        prizeRepository.deleteById(id);
        if (prizeRepository.existsById(id)) {
            throw ExceptionUtil.throwEntityDeletionException(Prize.class, id.toString());
        }
        return true;
    }

    @Override
    public Set<PrizeFullDto> findAll() {
        return prizeRepository.findAll().stream()
                .map(prize -> mapper.map(prize, PrizeFullDto.class))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<PrizeFullDto> findByHackathonId(Long hackathonId) {
        return prizeRepository.findByHackathonId(hackathonId).stream()
                .map(prize -> mapper.map(prize, PrizeFullDto.class))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<PrizeFullDto> findByTeamId(Long teamId) {
        return prizeRepository.findByTeamId(teamId).stream()
                .map(prize -> mapper.map(prize, PrizeFullDto.class))
                .collect(Collectors.toSet());
    }
}
