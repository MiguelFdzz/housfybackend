package fdz.migue.housfybackend.service;

import fdz.migue.housfybackend.entity.ChatGroup;
import fdz.migue.housfybackend.entity.House;
import fdz.migue.housfybackend.repository.ChatGroupRepository;
import fdz.migue.housfybackend.repository.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ChatGroupService {

    @Autowired
    private ChatGroupRepository chatGroupRepository;
    @Autowired
    private HouseRepository houseRepository;

    @Transactional(readOnly = true)
    public List<ChatGroup> findAll(){
        return chatGroupRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<ChatGroup> findById(Long id){
        return chatGroupRepository.findById(id);
    }

    @Transactional
    public ChatGroup create(ChatGroup chatGroup){
        House house = houseRepository.findById(chatGroup.getHouse().getHouseId())
                .orElseThrow(() -> new RuntimeException("House not found with id " + chatGroup.getHouse().getHouseId()));
        chatGroup.setHouse(house);
        return chatGroupRepository.save(chatGroup);
    }

    @Transactional
    public ChatGroup save(ChatGroup chatGroup){
        return chatGroupRepository.save(chatGroup);
    }

    @Transactional
    public ChatGroup updateById(Long id, ChatGroup chatGroupDetails){
        ChatGroup foundChatGroup = chatGroupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chat Group with id " + id + " not found"));

        foundChatGroup.setName(chatGroupDetails.getName());
        foundChatGroup.setDescription(chatGroupDetails.getDescription());
        foundChatGroup.setLastMessage(chatGroupDetails.getLastMessage());

        if (chatGroupDetails.getHouse() != null && chatGroupDetails.getHouse().getHouseId() != null) {
            House house = houseRepository.findById(chatGroupDetails.getHouse().getHouseId())
                    .orElseThrow(() -> new RuntimeException("House not found with id " + chatGroupDetails.getHouse().getHouseId()));
            foundChatGroup.setHouse(house);
        }

        return chatGroupRepository.save(foundChatGroup);
    }

    @Transactional
    public void delete(Long id){
        if (!chatGroupRepository.existsById(id)) {
            throw new RuntimeException("Chat Group with id " + id + " not found");
        }
        chatGroupRepository.deleteById(id);
    }
}