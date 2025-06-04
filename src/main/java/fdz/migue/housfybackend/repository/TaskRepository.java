package fdz.migue.housfybackend.repository;

import fdz.migue.housfybackend.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    List<Task> findByHouse_HouseId(Long houseId);

    List<Task> findByCategory_CategoryId(Long categoryId);
}