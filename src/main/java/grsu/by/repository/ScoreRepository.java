package grsu.by.repository;

import grsu.by.entity.Score;
import grsu.by.entity.ScoreId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreRepository extends CrudRepository<Score, ScoreId> {

}
