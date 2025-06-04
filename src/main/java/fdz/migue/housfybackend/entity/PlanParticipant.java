package fdz.migue.housfybackend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@Table(name = "plan_participants")
@NoArgsConstructor
@AllArgsConstructor
public class PlanParticipant {

    @EmbeddedId
    private PlanParticipantId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("planId")
    @JoinColumn(name = "plan_id", nullable = false)
    private Plan plan;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "confirmed")
    private Boolean confirmed = false;

    public PlanParticipant(Plan plan, User user) {
        this.plan = plan;
        this.user = user;
        this.id = new PlanParticipantId(plan.getPlanId(), user.getUserId());
    }
}

