package fdz.migue.housfybackend.repository;

import fdz.migue.housfybackend.entity.ChatGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatGroupRepository extends JpaRepository<ChatGroup,Long> {
}
