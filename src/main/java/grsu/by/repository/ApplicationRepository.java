package grsu.by.repository;

import grsu.by.entity.Application;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends CrudRepository<Application, Long> {
    boolean existsByTeamIdAndHackathonId(Long teamId, Long hackathonId);
}
