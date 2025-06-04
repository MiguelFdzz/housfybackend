package fdz.migue.housfybackend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanParticipantId implements Serializable {
    @Column(name = "plan_id")
    private Long planId;

    @Column(name = "user_id")
    private Long userId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlanParticipantId that = (PlanParticipantId) o;
        return planId.equals(that.planId) && userId.equals(that.userId);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(planId, userId);
    }
}
