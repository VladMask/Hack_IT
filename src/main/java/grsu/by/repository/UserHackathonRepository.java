package grsu.by.repository;

import grsu.by.entity.UserHackathon;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserHackathonRepository extends CrudRepository<UserHackathon, Long> {

}
