package grsu.by.repository;

import grsu.by.entity.Hackathon;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HackathonRepository extends CrudRepository<Hackathon, Long> {

}
