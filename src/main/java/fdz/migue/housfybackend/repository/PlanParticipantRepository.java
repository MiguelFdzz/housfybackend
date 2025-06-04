package fdz.migue.housfybackend.repository;

import fdz.migue.housfybackend.entity.PlanParticipant;
import fdz.migue.housfybackend.entity.PlanParticipantId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanParticipantRepository extends JpaRepository<PlanParticipant, PlanParticipantId> {
}