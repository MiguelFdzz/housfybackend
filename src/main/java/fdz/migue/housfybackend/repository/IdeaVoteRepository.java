package fdz.migue.housfybackend.repository;

import fdz.migue.housfybackend.entity.IdeaVote;
import fdz.migue.housfybackend.entity.IdeaVoteId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdeaVoteRepository extends JpaRepository<IdeaVote, IdeaVoteId> {
}
