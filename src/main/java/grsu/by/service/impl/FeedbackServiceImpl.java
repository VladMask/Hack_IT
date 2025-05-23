package grsu.by.service.impl;

import grsu.by.dto.feedbackDto.FeedbackCreationDto;
import grsu.by.dto.feedbackDto.FeedbackFullDto;
import grsu.by.entity.Feedback;
import grsu.by.entity.Hackathon;
import grsu.by.entity.Solution;
import grsu.by.entity.User;
import grsu.by.repository.FeedbackRepository;
import grsu.by.repository.HackathonRepository;
import grsu.by.repository.SolutionRepository;
import grsu.by.repository.UserRepository;
import grsu.by.service.FeedbackService;
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
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final UserRepository userRepository;
    private final SolutionRepository solutionRepository;
    private final HackathonRepository hackathonRepository;
    private final ModelMapper mapper;

    @Override
    public FeedbackCreationDto create(FeedbackCreationDto dto) {
        User judge = userRepository.findById(dto.getJudgeId()).orElseThrow(
                () -> ExceptionUtil.throwEntityNotFoundException(User.class, dto.getJudgeId().toString())
        );
        Solution solution = solutionRepository.findById(dto.getSolutionId()).orElseThrow(
                () -> ExceptionUtil.throwEntityNotFoundException(Solution.class, dto.getSolutionId().toString())
        );
        Hackathon hackathon = hackathonRepository.findById(dto.getHackathonId()).orElseThrow(
                () -> ExceptionUtil.throwEntityNotFoundException(Hackathon.class, dto.getHackathonId().toString())
        );

        Set<User> judges = userRepository.findJudgesByHackathonId(dto.getHackathonId());
        Set<Solution> solutions = solutionRepository.findByHackathonId(dto.getHackathonId());
        if (!judges.contains(judge)) {
            throw new IllegalStateException("User is not a judge for this hackathon");
        }

        if (!solutions.contains(solution)) {
            throw new IllegalStateException("Solution does not belong to the specified hackathon");
        }

        Feedback feedback = mapper.map(dto, Feedback.class);
        feedback.setJudge(judge);
        feedback.setSolution(solution);
        feedback.setHackathon(hackathon);

        return mapper.map(feedbackRepository.save(feedback), FeedbackCreationDto.class);
    }

    @Override
    public FeedbackFullDto findById(Long id) {
        Feedback feedback = feedbackRepository.findById(id).orElseThrow(
                () -> ExceptionUtil.throwEntityNotFoundException(Feedback.class, id.toString())
        );
        return mapper.map(feedback, FeedbackFullDto.class);
    }

    @Override
    public FeedbackFullDto update(Long id, FeedbackFullDto newDto) {
        Feedback feedback = feedbackRepository.findById(id).orElseThrow(
                () -> ExceptionUtil.throwEntityNotFoundException(Feedback.class, id.toString())
        );
        
        if (newDto.getContent() != null && !StringUtils.EMPTY.equals(newDto.getContent())){
            feedback.setContent(newDto.getContent());
        }

        return mapper.map(feedbackRepository.save(feedback), FeedbackFullDto.class);
    }

    @Override
    public boolean deleteById(Long id) {
        feedbackRepository.deleteById(id);
        if (feedbackRepository.existsById(id)) {
            throw ExceptionUtil.throwEntityDeletionException(Feedback.class, id.toString());
        }
        return true;
    }

    @Override
    public Set<FeedbackFullDto> findByJudgeId(Long judgeId) {
        return feedbackRepository.findAllByJudgeId(judgeId).stream()
                .map(f -> mapper.map(f, FeedbackFullDto.class))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<FeedbackFullDto> findBySolutionId(Long solutionId) {
        return feedbackRepository.findAllBySolutionId(solutionId).stream()
                .map(f -> mapper.map(f, FeedbackFullDto.class))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<FeedbackFullDto> findByHackathonId(Long hackathonId) {
        return feedbackRepository.findAllByHackathonId(hackathonId).stream()
                .map(f -> mapper.map(f, FeedbackFullDto.class))
                .collect(Collectors.toSet());
    }
}
