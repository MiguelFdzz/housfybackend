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
public class IdeaVoteId implements Serializable {
    @Column(name = "idea_id")
    private Long ideaId;

    @Column(name = "user_id")
    private Long userId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdeaVoteId that = (IdeaVoteId) o;
        return ideaId.equals(that.ideaId) && userId.equals(that.userId);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(ideaId, userId);
    }
}