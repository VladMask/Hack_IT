package grsu.by.service.impl;

import grsu.by.dto.ScoreDto;
import grsu.by.entity.Score;
import grsu.by.entity.ScoreId;
import grsu.by.entity.Solution;
import grsu.by.entity.User;
import grsu.by.repository.ScoreRepository;
import grsu.by.repository.SolutionRepository;
import grsu.by.repository.UserRepository;
import grsu.by.service.ScoreService;
import grsu.by.util.ExceptionUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Transactional
public class ScoreServiceImpl implements ScoreService {

    private final ScoreRepository scoreRepository;
    private final SolutionRepository solutionRepository;
    private final UserRepository userRepository;
    private final ModelMapper mapper;

    @Override
    public ScoreDto create(ScoreDto dto) {
        Score score = mapper.map(dto, Score.class);
        User judge = userRepository.findById(dto.getJudgeId()).orElseThrow(
                () -> ExceptionUtil.throwEntityNotFoundException(User.class, dto.getJudgeId().toString())
        );
        Solution solution = solutionRepository.findById(dto.getSolutionId()).orElseThrow(
                () -> ExceptionUtil.throwEntityNotFoundException(User.class, dto.getSolutionId().toString())
        );
        score.setJudge(judge);
        score.setSolution(solution);
        return mapper.map(scoreRepository.save(score), ScoreDto.class);
    }

    @Override
    public ScoreDto findById(Long id) {
        Score score = scoreRepository.findById(new ScoreId(id, id)).orElseThrow(
                () -> ExceptionUtil.throwEntityNotFoundException(Score.class, id.toString())
        );
        return mapper.map(score, ScoreDto.class);
    }

    @Override
    public ScoreDto update(Long id, ScoreDto newDto) {
        Score score = scoreRepository.findById(new ScoreId(id, id)).orElseThrow(
                () -> ExceptionUtil.throwEntityNotFoundException(Score.class, id.toString())
        );
        
        if (newDto.getValue() != null) {
            score.setValue(newDto.getValue());
        }
        
        return mapper.map(scoreRepository.save(score), ScoreDto.class);
    }

    @Override
    public boolean deleteById(Long id) {
        scoreRepository.deleteById(new ScoreId(id, id));
        if (scoreRepository.existsById(new ScoreId(id, id))) {
            throw ExceptionUtil.throwEntityDeletionException(Score.class, id.toString());
        }
        return true;
    }
}
