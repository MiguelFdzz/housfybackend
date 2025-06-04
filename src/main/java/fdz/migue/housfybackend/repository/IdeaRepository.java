package fdz.migue.housfybackend.repository;

import fdz.migue.housfybackend.entity.Idea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdeaRepository extends JpaRepository<Idea,Long> {
}
