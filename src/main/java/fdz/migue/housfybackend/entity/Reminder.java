package fdz.migue.housfybackend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Data
@Table(name = "reminder")
@NoArgsConstructor
@AllArgsConstructor
public class Reminder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    @Column(name = "reminder_date", nullable = false)
    private Timestamp reminderDate;

    @Column(name = "message")
    private String message;

    @Column(name = "sent")
    private Boolean sent = false;

    @Column(name = "creation_date")
    private Timestamp creationDate;

    @PrePersist
    protected void onCreate() {
        if (this.creationDate == null) {
            this.creationDate = Timestamp.from(Instant.now());
        }
    }
}