package fdz.migue.housfybackend.entity;

import fdz.migue.housfybackend.enums.PlanStatus;
import com.vladmihalcea.hibernate.type.array.ListArrayType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.Type;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "plans")
@NoArgsConstructor
@AllArgsConstructor
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plan_id")
    private Long planId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idea_id", unique = true)
    private Idea idea;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private PlanCategory category;

    @Column(name = "budget", precision = 10, scale = 2)
    private BigDecimal budget;


    @Type(value = ListArrayType.class)
    @Column(name = "days", columnDefinition = "date[]")
    private List<LocalDate> days = new ArrayList<>();

    @Column(name = "suggested_place")
    private String suggestedPlace;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proposer_user_id", nullable = false)
    private User proposerUser;

    @Column(name = "creation_date")
    private Timestamp creationDate;

    @Column(name = "update_date")
    private Timestamp updateDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private PlanStatus status;

    @Column(name = "materiales")
    private String materiales;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "house_id", nullable = false)
    private House house;

    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PlanParticipant> participants = new HashSet<>();

    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PlanPhoto> photos = new HashSet<>();

    @PrePersist
    protected void onCreate() {
        if (this.creationDate == null) {
            this.creationDate = Timestamp.from(Instant.now());
        }
        if (this.updateDate == null) {
            this.updateDate = Timestamp.from(Instant.now());
        }
        if (this.status == null) {
            this.status = PlanStatus.PENDIENTE;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        this.updateDate = Timestamp.from(Instant.now());
    }
}