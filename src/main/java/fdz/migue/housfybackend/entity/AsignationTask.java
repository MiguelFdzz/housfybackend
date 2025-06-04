package fdz.migue.housfybackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Data
@Table(name = "asignations_tasks")
@NoArgsConstructor
@AllArgsConstructor
public class AsignationTask {

    @EmbeddedId
    private AsignationTaskId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("taskId")
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "assign_date")
    private Timestamp assignDate;

    @PrePersist
    protected void onCreate() {
        if (this.assignDate == null) {
            this.assignDate = Timestamp.from(Instant.now());
        }
    }

    public AsignationTask(Task task, User user) {
        this.task = task;
        this.user = user;
        this.id = new AsignationTaskId(task.getId(), user.getUserId());
        this.assignDate = Timestamp.from(Instant.now());
    }
}