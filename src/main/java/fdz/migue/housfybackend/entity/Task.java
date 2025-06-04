package fdz.migue.housfybackend.entity;

import fdz.migue.housfybackend.enums.TaskTypeState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Set;

@Entity
@Data
@Table(name = "Task")
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "category", nullable = false)
    private TaskCategory category;

    @Column(name = "exp_date")
    private LocalDate expDate;

    @Column(name = "day")
    private OffsetDateTime day;

    @Column(name = "recurrence")
    private Integer recurrence;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskTypeState state;

    @Column(name = "priority")
    private Double priority;

    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Timestamp creationDate;

    @Column(name = "update_date")
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Timestamp updateDate;

    @Column(name = "finish_date")
    private Timestamp finishDate;

    @ManyToOne
    @JoinColumn(name = "house_id", nullable = false)
    private House house;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AsignationTask> assignations;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Reminder> reminders;
}
