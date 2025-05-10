package grsu.by.repository;

import grsu.by.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Query("""
    select u from User u
            left join fetch u.userRoles ur
            left join fetch ur.role r
            left join fetch u.credentials c
            where c.email = :email
    """)
    Optional<User> findByEmail(@Param("email") String email);

}
