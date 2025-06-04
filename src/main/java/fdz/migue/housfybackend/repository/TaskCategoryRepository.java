package fdz.migue.housfybackend.repository;

import fdz.migue.housfybackend.entity.TaskCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskCategoryRepository extends JpaRepository<TaskCategory,Long> {
}
