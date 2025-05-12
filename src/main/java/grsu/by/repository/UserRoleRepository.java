package grsu.by.repository;

import grsu.by.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    Optional<UserRole> findByUserIdAndRoleName(Long userId, String roleName);

    boolean existsByUserIdAndRoleName(Long userId, String roleName);

    void deleteByUserIdAndRoleName(Long userId, String roleName);

}
