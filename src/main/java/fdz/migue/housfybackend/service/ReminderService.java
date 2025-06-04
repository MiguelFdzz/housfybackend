package fdz.migue.housfybackend.service;

import fdz.migue.housfybackend.entity.Reminder;
import fdz.migue.housfybackend.entity.User;
import fdz.migue.housfybackend.repository.ReminderRepository;
import fdz.migue.housfybackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class ReminderService {

    @Autowired
    private ReminderRepository reminderRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<Reminder> findAll(){
        return reminderRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Reminder> findById(Long id){
        return reminderRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Reminder> findRemindersByUserId(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User not found with id " + userId);
        }
        return reminderRepository.findByUser_UserIdOrderByReminderDateAsc(userId);
    }

    @Transactional(readOnly = true)
    public List<Reminder> findRemindersByUserIdAndDateRange(Long userId, Timestamp startDate, Timestamp endDate) {
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User not found with id " + userId);
        }
        return reminderRepository.findByUser_UserIdAndReminderDateBetweenOrderByReminderDateAsc(userId, startDate, endDate);
    }


    @Transactional
    public Reminder create(Reminder reminder){
        User user = userRepository.findById(reminder.getUser().getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id " + reminder.getUser().getUserId()));
        reminder.setUser(user);

        if (reminder.getIsCompleted() == null) {
            reminder.setIsCompleted(false);
        }

        return reminderRepository.save(reminder);
    }

    @Transactional
    public Reminder update(Reminder reminder){
        return reminderRepository.save(reminder);
    }

    @Transactional
    public Reminder updateById(Long id, Reminder reminderDetails){
        Reminder foundReminder = reminderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reminder with id " + id + " not found"));

        foundReminder.setTitle(reminderDetails.getTitle());
        foundReminder.setDescription(reminderDetails.getDescription());
        foundReminder.setReminderDate(reminderDetails.getReminderDate());
        foundReminder.setIsCompleted(reminderDetails.getIsCompleted());

        if (reminderDetails.getUser() != null && reminderDetails.getUser().getUserId() != null) {
            User user = userRepository.findById(reminderDetails.getUser().getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found with id " + reminderDetails.getUser().getUserId()));
            foundReminder.setUser(user);
        } else {
            if (reminderDetails.getUser() == null || reminderDetails.getUser().getUserId() == null) {
                throw new IllegalArgumentException("User ID cannot be null for Reminder update.");
            }
        }

        return reminderRepository.save(foundReminder);
    }

    @Transactional
    public void delete(Long id){
        if (!reminderRepository.existsById(id)) {
            throw new RuntimeException("Reminder with id " + id + " not found");
        }
        reminderRepository.deleteById(id);
    }
}