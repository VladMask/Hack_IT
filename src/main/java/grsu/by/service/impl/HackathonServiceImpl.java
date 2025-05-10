package grsu.by.service.impl;

import grsu.by.dto.hackathonDto.HackathonCreationDto;
import grsu.by.dto.hackathonDto.HackathonFullDto;
import grsu.by.entity.Hackathon;
import grsu.by.entity.User;
import grsu.by.repository.HackathonRepository;
import grsu.by.repository.UserRepository;
import grsu.by.service.HackathonService;
import grsu.by.util.ExceptionUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@RequiredArgsConstructor
@Service
@Transactional
public class HackathonServiceImpl implements HackathonService {

    private final HackathonRepository hackathonRepository;
    private final UserRepository userRepository;
    private final ModelMapper mapper;

    @Override
    public HackathonCreationDto create(HackathonCreationDto dto) {
        Hackathon hackathon = mapper.map(dto, Hackathon.class);
        return mapper.map(hackathonRepository.save(hackathon), HackathonCreationDto.class);
    }

    @Override
    public HackathonFullDto findById(Long id) {
        Hackathon hackathon = hackathonRepository.findById(id).orElseThrow(
                () -> ExceptionUtil.throwEntityNotFoundException(Hackathon.class, id.toString())
        );
        return mapper.map(hackathon, HackathonFullDto.class);
    }

    @Override
    public HackathonFullDto update(Long id, HackathonFullDto newDto) {
        Hackathon hackathon = hackathonRepository.findById(id).orElseThrow(
                () -> ExceptionUtil.throwEntityNotFoundException(Hackathon.class, id.toString())
        );
        
        if (newDto.getName() != null) {
            hackathon.setName(newDto.getName());
        }
        if (newDto.getDescription() != null) {
            hackathon.setDescription(newDto.getDescription());
        }
        if (newDto.getStartDate() != null) {
            hackathon.setStartDate(newDto.getStartDate());
        }
        if (newDto.getEndDate() != null) {
            hackathon.setEndDate(newDto.getEndDate());
        }
        if (newDto.getPhotosUrl() != null) {
            hackathon.setPhotosUrl(newDto.getPhotosUrl());
        }
        if (newDto.getIsFinished() != null) {
            hackathon.setIsFinished(newDto.getIsFinished());
        }
        if (newDto.getRegistrationStart() != null) {
            hackathon.setRegistrationStart(newDto.getRegistrationStart());
        }
        if (newDto.getRegistrationEnd() != null) {
            hackathon.setRegistrationEnd(newDto.getRegistrationEnd());
        }
        if (newDto.getDevelopmentStart() != null) {
            hackathon.setDevelopmentStart(newDto.getDevelopmentStart());
        }
        if (newDto.getDevelopmentEnd() != null) {
            hackathon.setDevelopmentEnd(newDto.getDevelopmentEnd());
        }
        if (newDto.getAssessmentStart() != null) {
            hackathon.setAssessmentStart(newDto.getAssessmentStart());
        }
        if (newDto.getAssessmentEnd() != null) {
            hackathon.setAssessmentEnd(newDto.getAssessmentEnd());
        }
        if (newDto.getResultsAnnouncement() != null) {
            hackathon.setResultsAnnouncement(newDto.getResultsAnnouncement());
        }
        
        return mapper.map(hackathonRepository.save(hackathon), HackathonFullDto.class);
    }

    @Override
    public boolean deleteById(Long id) {
        hackathonRepository.deleteById(id);
        if (hackathonRepository.existsById(id)) {
            throw ExceptionUtil.throwEntityDeletionException(Hackathon.class, id.toString());
        }
        return true;
    }

    @Override
    public boolean addJudge(Long hackathonId, Long userId) {
        Hackathon hackathon = hackathonRepository.findById(hackathonId).orElseThrow(
                () -> ExceptionUtil.throwEntityNotFoundException(Hackathon.class, hackathonId.toString())
        );
        User judge = userRepository.findById(userId).orElseThrow(
                () -> ExceptionUtil.throwEntityNotFoundException(User.class, userId.toString())
        );
        
        if (hackathon.getJudges().contains(judge)) {
            return false;
        }
        
        hackathon.getJudges().add(judge);
        hackathonRepository.save(hackathon);
        return true;
    }

    @Override
    public boolean removeJudge(Long hackathonId, Long userId) {
        Hackathon hackathon = hackathonRepository.findById(hackathonId).orElseThrow(
                () -> ExceptionUtil.throwEntityNotFoundException(Hackathon.class, hackathonId.toString())
        );
        User judge = userRepository.findById(userId).orElseThrow(
                () -> ExceptionUtil.throwEntityNotFoundException(User.class, userId.toString())
        );
        
        if (!hackathon.getJudges().contains(judge)) {
            return false;
        }
        
        hackathon.getJudges().remove(judge);
        hackathonRepository.save(hackathon);
        return true;
    }

    @Override
    public boolean finishHackathon(Long id) {
        Hackathon hackathon = hackathonRepository.findById(id).orElseThrow(
                () -> ExceptionUtil.throwEntityNotFoundException(Hackathon.class, id.toString())
        );
        
        if (hackathon.getIsFinished()) {
            return false;
        }
        
        hackathon.setIsFinished(true);
        hackathon.setEndDate(OffsetDateTime.now());
        hackathonRepository.save(hackathon);
        return true;
    }
}
