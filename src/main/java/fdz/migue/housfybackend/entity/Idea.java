package fdz.migue.housfybackend.entity;

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
@Table(name = "ideas")
@NoArgsConstructor
@AllArgsConstructor
public class Idea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idea_id")
    private Long ideaId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private PlanCategory category;

    @Column(name = "budget", precision = 10, scale = 2)
    private BigDecimal budget;

    @Column(name = "suggested_participants")
    private String suggestedParticipants;

    @Type(value = ListArrayType.class)
    @Column(name = "days", columnDefinition = "date[]")
    private List<LocalDate> days = new ArrayList<>();

    @Column(name = "suggested_place")
    private String suggestedPlace;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proposer_user_id")
    private User proposerUser;

    @Column(name = "creation_date")
    private Timestamp creationDate;

    @Column(name = "is_converted_to_plan")
    private Boolean isConvertedToPlan = false;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "house_id", nullable = false)
    private House house;

    @OneToMany(mappedBy = "idea", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<IdeaVote> ideaVotes = new HashSet<>();

    @OneToOne(mappedBy = "idea", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Plan plan;

    @PrePersist
    protected void onCreate() {
        if (this.creationDate == null) {
            this.creationDate = Timestamp.from(Instant.now());
        }
        if (this.isConvertedToPlan == null) {
            this.isConvertedToPlan = false;
        }
    }
}