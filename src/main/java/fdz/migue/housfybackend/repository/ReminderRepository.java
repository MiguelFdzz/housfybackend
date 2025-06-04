package fdz.migue.housfybackend.repository;

import fdz.migue.housfybackend.entity.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface ReminderRepository extends JpaRepository<Reminder,Long> {
    List<Reminder> findByUser_UserIdOrderByReminderDateAsc(Long userId);

    List<Reminder> findByUser_UserIdAndReminderDateBetweenOrderByReminderDateAsc(Long userId, Timestamp startDate, Timestamp endDate);
}