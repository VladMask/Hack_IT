package grsu.by.repository;

import grsu.by.entity.UserTeam;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTeamRepository extends CrudRepository<UserTeam, Long> {

}
