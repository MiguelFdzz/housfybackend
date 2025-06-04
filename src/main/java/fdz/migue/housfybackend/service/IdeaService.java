package fdz.migue.housfybackend.service;

import fdz.migue.housfybackend.entity.Idea;
import fdz.migue.housfybackend.entity.PlanCategory;
import fdz.migue.housfybackend.entity.User;
import fdz.migue.housfybackend.entity.House;
import fdz.migue.housfybackend.repository.IdeaRepository;
import fdz.migue.housfybackend.repository.PlanCategoryRepository;
import fdz.migue.housfybackend.repository.UserRepository;
import fdz.migue.housfybackend.repository.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class IdeaService {

    @Autowired
    private IdeaRepository ideaRepository;
    @Autowired
    private PlanCategoryRepository planCategoryRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HouseRepository houseRepository;

    @Transactional(readOnly = true)
    public List<Idea> findAll(){
        return ideaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Idea> findById(Long id){
        return ideaRepository.findById(id);
    }

    @Transactional
    public Idea create(Idea idea){
        if (idea.getCategory() != null && idea.getCategory().getCategoryId() != null) {
            PlanCategory category = planCategoryRepository.findById(idea.getCategory().getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Plan Category not found with id " + idea.getCategory().getCategoryId()));
            idea.setCategory(category);
        }

        User proposerUser = userRepository.findById(idea.getProposerUser().getUserId())
                .orElseThrow(() -> new RuntimeException("Proposer User not found with id " + idea.getProposerUser().getUserId()));
        idea.setProposerUser(proposerUser);

        House house = houseRepository.findById(idea.getHouse().getHouseId())
                .orElseThrow(() -> new RuntimeException("House not found with id " + idea.getHouse().getHouseId()));
        idea.setHouse(house);

        return ideaRepository.save(idea);
    }

    @Transactional
    public Idea save(Idea idea){
        return ideaRepository.save(idea);
    }

    @Transactional
    public Idea updateById(Long id, Idea ideaDetails){
        Idea foundIdea = ideaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Idea with id " + id + " not found"));

        foundIdea.setTitle(ideaDetails.getTitle());
        foundIdea.setDescription(ideaDetails.getDescription());
        foundIdea.setBudget(ideaDetails.getBudget());
        foundIdea.setSuggestedParticipants(ideaDetails.getSuggestedParticipants());
        foundIdea.setDays(ideaDetails.getDays());
        foundIdea.setSuggestedPlace(ideaDetails.getSuggestedPlace());
        foundIdea.setIsConvertedToPlan(ideaDetails.getIsConvertedToPlan());

        if (ideaDetails.getCategory() != null && ideaDetails.getCategory().getCategoryId() != null) {
            PlanCategory category = planCategoryRepository.findById(ideaDetails.getCategory().getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Plan Category not found with id " + ideaDetails.getCategory().getCategoryId()));
            foundIdea.setCategory(category);
        } else {
            foundIdea.setCategory(null);
        }

        if (ideaDetails.getProposerUser() != null && ideaDetails.getProposerUser().getUserId() != null) {
            User proposerUser = userRepository.findById(ideaDetails.getProposerUser().getUserId())
                    .orElseThrow(() -> new RuntimeException("Proposer User not found with id " + ideaDetails.getProposerUser().getUserId()));
            foundIdea.setProposerUser(proposerUser);
        }

        if (ideaDetails.getHouse() != null && ideaDetails.getHouse().getHouseId() != null) {
            House house = houseRepository.findById(ideaDetails.getHouse().getHouseId())
                    .orElseThrow(() -> new RuntimeException("House not found with id " + ideaDetails.getHouse().getHouseId()));
            foundIdea.setHouse(house);
        }

        return ideaRepository.save(foundIdea);
    }

    @Transactional
    public void delete(Long id){
        if (!ideaRepository.existsById(id)) {
            throw new RuntimeException("Idea with id " + id + " not found");
        }
        ideaRepository.deleteById(id);
    }
}