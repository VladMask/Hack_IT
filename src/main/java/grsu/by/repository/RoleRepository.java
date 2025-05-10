package grsu.by.repository;

import grsu.by.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role, Short> {

    Optional<Role> findByName(String name);

}
