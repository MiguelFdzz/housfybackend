package fdz.migue.housfybackend.repository;

import fdz.migue.housfybackend.entity.PlanPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanPhotoRepository extends JpaRepository<PlanPhoto,Long> {
}
