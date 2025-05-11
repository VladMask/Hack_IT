package grsu.by.repository;

import grsu.by.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    @Query("""
        select n from Notification n
        join n.users u
        where u.id = :userId
    """)
    Set<Notification> findAllByUserId(@Param("userId") Long userId);
    Set<Notification> findAllByHackathonId(Long hackathonId);
}
