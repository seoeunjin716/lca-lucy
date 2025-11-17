package store.esgseed.api.soccer.schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
