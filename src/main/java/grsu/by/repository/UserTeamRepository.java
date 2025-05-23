package grsu.by.repository;

import grsu.by.entity.UserTeam;
import grsu.by.entity.UserTeamId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTeamRepository extends JpaRepository<UserTeam, UserTeamId> {

}
