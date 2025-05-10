package grsu.by.repository;

import grsu.by.entity.Prize;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrizeRepository extends CrudRepository<Prize, Long> {

}
