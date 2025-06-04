package fdz.migue.housfybackend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Data
@Table(name = "idea_votes")
@NoArgsConstructor
@AllArgsConstructor
public class IdeaVote {

    @EmbeddedId
    private IdeaVoteId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("ideaId")
    @JoinColumn(name = "idea_id", nullable = false)
    private Idea idea;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "vote_type")
    private Short voteType;

    @Column(name = "creation_date")
    private Timestamp creationDate;

    @PrePersist
    protected void onCreate() {
        if (this.creationDate == null) {
            this.creationDate = Timestamp.from(Instant.now());
        }
    }

    public IdeaVote(Idea idea, User user, Short voteType) {
        this.idea = idea;
        this.user = user;
        this.voteType = voteType;
        this.id = new IdeaVoteId(idea.getIdeaId(), user.getUserId());
        this.creationDate = Timestamp.from(Instant.now());
    }
}