package fdz.migue.housfybackend.repository;

import fdz.migue.housfybackend.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message,Long> {
    List<Message> findByChatGroup_ChatGroupIdOrderByCreationDateAsc(Long chatGroupId);
}
