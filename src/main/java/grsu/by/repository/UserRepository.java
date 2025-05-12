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

    @Query(value = """
                    select u.* from users u
                    join user_roles ur on u.id = ur.user_id
                    join roles r on ur.role_id = r.id
                    join user_hackathons uh on ur.id = uh.user_role_id
                    where r.name = 'JUDGE' and uh.hackathon_id = :hackathonId
                """,
            nativeQuery = true)
    Set<User> findJudgesByHackathonId(@Param("hackathonId") Long hackathonId);

}
