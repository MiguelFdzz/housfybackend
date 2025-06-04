package fdz.migue.housfybackend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "name")
    private String name;

    @Column(name = "state")
    private String state;

    @ManyToMany
    @JoinTable(
            name = "user_house",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "house_id")
    )
    private Set<House> houses = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AsignationTask> asignationsTasks = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<IdeaVote> ideaVotes = new HashSet<>();

    @OneToMany(mappedBy = "proposerUser", cascade = CascadeType.ALL)
    private Set<Idea> proposedIdeas = new HashSet<>();

    @OneToMany(mappedBy = "proposerUser", cascade = CascadeType.ALL)
    private Set<Plan> proposedPlans = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PlanParticipant> planParticipants = new HashSet<>();

    @OneToMany(mappedBy = "uploadUser", cascade = CascadeType.ALL)
    private Set<PlanPhoto> uploadedPhotos = new HashSet<>();

    @OneToMany(mappedBy = "senderUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Message> sentMessages = new HashSet<>();
}