package grsu.by.service.impl;

import grsu.by.dto.PrizeDto;
import grsu.by.entity.Hackathon;
import grsu.by.entity.Prize;
import grsu.by.entity.TeamHackathon;
import grsu.by.repository.HackathonRepository;
import grsu.by.repository.PrizeRepository;
import grsu.by.repository.TeamHackathonRepository;
import grsu.by.service.PrizeService;
import grsu.by.util.ExceptionUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class PrizeServiceImpl implements PrizeService {

    private final PrizeRepository prizeRepository;
    private final HackathonRepository hackathonRepository;
    private final TeamHackathonRepository teamHackathonRepository;
    private final ModelMapper mapper;

    @Override
    public PrizeDto create(PrizeDto dto) {
        Prize prize = mapper.map(dto, Prize.class);
        Hackathon hackathon = hackathonRepository.findById(dto.getHackathonId()).orElseThrow(
                () -> ExceptionUtil.throwEntityNotFoundException(Hackathon.class, dto.getHackathonId().toString())
        );
        prize.setHackathon(hackathon);
        return mapper.map(prizeRepository.save(prize), PrizeDto.class);
    }

    @Override
    public PrizeDto findById(Long id) {
        Prize prize = prizeRepository.findById(id).orElseThrow(
                () -> ExceptionUtil.throwEntityNotFoundException(Prize.class, id.toString())
        );
        return mapper.map(prize, PrizeDto.class);
    }

    @Override
    public PrizeDto update(Long id, PrizeDto newDto) {
        Prize prize = prizeRepository.findById(id).orElseThrow(
                () -> ExceptionUtil.throwEntityNotFoundException(Prize.class, id.toString())
        );
        
        if (newDto.getName() != null) {
            prize.setName(newDto.getName());
        }
        if (newDto.getQuantity() != null) {
            prize.setQuantity(newDto.getQuantity());
        }
        if(newDto.getTeamHackathonId() != null){
            TeamHackathon teamHackathon = teamHackathonRepository.findById(newDto.getTeamHackathonId()).orElseThrow(
                    () -> ExceptionUtil.throwEntityNotFoundException(TeamHackathon.class, newDto.getTeamHackathonId().toString())
            );
            prize.setTeamHackathon(teamHackathon);
        }
        
        return mapper.map(prizeRepository.save(prize), PrizeDto.class);
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
    public List<PrizeDto> findAll() {
        return List.of();
    }

    @Override
    public List<PrizeDto> findByHackathonId(Long hackathonId) {
        return List.of();
    }

    @Override
    public List<PrizeDto> findByTeamId(Long teamId) {
        return List.of();
    }
}
