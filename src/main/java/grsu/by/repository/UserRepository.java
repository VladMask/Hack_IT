package grsu.by.repository;

import grsu.by.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("""
    select u from User u
            left join fetch u.userRoles ur
            left join fetch ur.role r
            left join fetch u.credentials c
            where c.email = :email
    """)
    Optional<User> findByEmail(@Param("email") String email);

    @Query("""
        select u from User u
        join UserRole ur on u.id = ur.user.id
        join Role r on ur.role.id = r.id
        join UserHackathon uh on ur.id = uh.userRoleId
        where r.name = 'JUDGE' and uh.hackathonId = :hackathonId
    """)
    Set<User> findJudgesByHackathonId(@Param("hackathonId") Long hackathonId);

}
