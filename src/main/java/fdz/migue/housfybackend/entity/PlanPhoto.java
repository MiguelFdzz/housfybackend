package fdz.migue.housfybackend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Data
@Table(name = "plan_photos")
@NoArgsConstructor
@AllArgsConstructor
public class PlanPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "photo_id")
    private Long photoId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id")
    private Plan plan;

    @Column(name = "photo_url")
    private String photoUrl;

    @Column(name = "caption")
    private String caption;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "upload_user_id")
    private User uploadUser;

    @Column(name = "upload_date")
    private Timestamp uploadDate;

    @PrePersist
    protected void onCreate() {
        if (this.uploadDate == null) {
            this.uploadDate = Timestamp.from(Instant.now());
        }
    }
}