package grsu.by.repository;

import grsu.by.entity.TeamHackathon;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamHackathonRepository extends CrudRepository<TeamHackathon, Long> {

}
