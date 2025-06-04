package fdz.migue.housfybackend.repository;

import fdz.migue.housfybackend.entity.PlanCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanCategoryRepository extends JpaRepository<PlanCategory,Long> {
}
