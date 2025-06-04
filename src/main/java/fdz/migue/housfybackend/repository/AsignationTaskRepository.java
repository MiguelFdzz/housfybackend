package fdz.migue.housfybackend.repository;

import fdz.migue.housfybackend.entity.AsignationTask;
import fdz.migue.housfybackend.entity.AsignationTaskId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsignationTaskRepository extends JpaRepository<AsignationTask, AsignationTaskId> {
}