package fdz.migue.housfybackend.service;

import fdz.migue.housfybackend.entity.Message;
import fdz.migue.housfybackend.entity.ChatGroup;
import fdz.migue.housfybackend.entity.User;
import fdz.migue.housfybackend.repository.MessageRepository;
import fdz.migue.housfybackend.repository.ChatGroupRepository;
import fdz.migue.housfybackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private ChatGroupRepository chatGroupRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<Message> findAll(){
        return messageRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Message> findById(Long id){
        return messageRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Message> findMessagesByChatGroupId(Long chatGroupId) {
        if (!chatGroupRepository.existsById(chatGroupId)) {
            throw new RuntimeException("Chat Group not found with id " + chatGroupId);
        }
        return messageRepository.findByChatGroup_ChatGroupIdOrderByCreationDateAsc(chatGroupId);
    }

    @Transactional
    public Message create(Message message){
        ChatGroup chatGroup = chatGroupRepository.findById(message.getChatGroup().getChatGroupId())
                .orElseThrow(() -> new RuntimeException("Chat Group not found with id " + message.getChatGroup().getChatGroupId()));
        User senderUser = userRepository.findById(message.getSenderUser().getUserId())
                .orElseThrow(() -> new RuntimeException("Sender User not found with id " + message.getSenderUser().getUserId()));

        message.setChatGroup(chatGroup);
        message.setSenderUser(senderUser);

        chatGroup.setLastMessage(message.getCreationDate());
        chatGroupRepository.save(chatGroup);

        return messageRepository.save(message);
    }

    @Transactional
    public Message save(Message message){
        return messageRepository.save(message);
    }

    @Transactional
    public Message updateById(Long id, Message messageDetails){
        Message foundMessage = messageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Message with id " + id + " not found"));

        foundMessage.setTextContent(messageDetails.getTextContent());
        foundMessage.setIsDeleted(messageDetails.getIsDeleted());

        return messageRepository.save(foundMessage);
    }

    @Transactional
    public void delete(Long id){
        Message messageToDelete = messageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Message with id " + id + " not found"));
        messageToDelete.setIsDeleted(true);
        messageRepository.save(messageToDelete);
    }
}