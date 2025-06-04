package fdz.migue.housfybackend.service;

import fdz.migue.housfybackend.entity.Plan;
import fdz.migue.housfybackend.entity.PlanCategory;
import fdz.migue.housfybackend.entity.User;
import fdz.migue.housfybackend.entity.House;
import fdz.migue.housfybackend.entity.Idea;
import fdz.migue.housfybackend.repository.PlanRepository;
import fdz.migue.housfybackend.repository.PlanCategoryRepository;
import fdz.migue.housfybackend.repository.UserRepository;
import fdz.migue.housfybackend.repository.HouseRepository;
import fdz.migue.housfybackend.repository.IdeaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class PlanService {

    @Autowired
    private PlanRepository planRepository;
    @Autowired
    private PlanCategoryRepository planCategoryRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HouseRepository houseRepository;
    @Autowired
    private IdeaRepository ideaRepository;

    @Transactional(readOnly = true)
    public List<Plan> findAll(){
        return planRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Plan> findById(Long id){
        return planRepository.findById(id);
    }

    @Transactional
    public Plan create(Plan plan){
        if (plan.getCategory() != null && plan.getCategory().getCategoryId() != null) {
            PlanCategory category = planCategoryRepository.findById(plan.getCategory().getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Plan Category not found with id " + plan.getCategory().getCategoryId()));
            plan.setCategory(category);
        }

        User proposerUser = userRepository.findById(plan.getProposerUser().getUserId())
                .orElseThrow(() -> new RuntimeException("Proposer User not found with id " + plan.getProposerUser().getUserId()));
        plan.setProposerUser(proposerUser);

        House house = houseRepository.findById(plan.getHouse().getHouseId())
                .orElseThrow(() -> new RuntimeException("House not found with id " + plan.getHouse().getHouseId()));
        plan.setHouse(house);

        if (plan.getIdea() != null && plan.getIdea().getIdeaId() != null) {
            Idea idea = ideaRepository.findById(plan.getIdea().getIdeaId())
                    .orElseThrow(() -> new RuntimeException("Idea not found with id " + plan.getIdea().getIdeaId()));
            if (idea.getIsConvertedToPlan() == Boolean.TRUE) {
                throw new RuntimeException("Idea with id " + idea.getIdeaId() + " has already been converted to a plan.");
            }
            plan.setIdea(idea);
            idea.setIsConvertedToPlan(true);
            ideaRepository.save(idea);
        }

        return planRepository.save(plan);
    }

    @Transactional
    public Plan update(Plan plan){
        return planRepository.save(plan);
    }

    @Transactional
    public Plan updateById(Long id, Plan planDetails){
        Plan foundPlan = planRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plan with id " + id + " not found"));

        foundPlan.setTitle(planDetails.getTitle());
        foundPlan.setDescription(planDetails.getDescription());
        foundPlan.setBudget(planDetails.getBudget());
        foundPlan.setDays(planDetails.getDays());
        foundPlan.setSuggestedPlace(planDetails.getSuggestedPlace());
        foundPlan.setStatus(planDetails.getStatus());
        foundPlan.setMateriales(planDetails.getMateriales());

        if (planDetails.getCategory() != null && planDetails.getCategory().getCategoryId() != null) {
            PlanCategory category = planCategoryRepository.findById(planDetails.getCategory().getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Plan Category not found with id " + planDetails.getCategory().getCategoryId()));
            foundPlan.setCategory(category);
        } else {
            foundPlan.setCategory(null);
        }

        if (planDetails.getProposerUser() != null && planDetails.getProposerUser().getUserId() != null) {
            User proposerUser = userRepository.findById(planDetails.getProposerUser().getUserId())
                    .orElseThrow(() -> new RuntimeException("Proposer User not found with id " + planDetails.getProposerUser().getUserId()));
            foundPlan.setProposerUser(proposerUser);
        }

        if (planDetails.getHouse() != null && planDetails.getHouse().getHouseId() != null) {
            House house = houseRepository.findById(planDetails.getHouse().getHouseId())
                    .orElseThrow(() -> new RuntimeException("House not found with id " + planDetails.getHouse().getHouseId()));
            foundPlan.setHouse(house);
        }


        if (planDetails.getIdea() != null && planDetails.getIdea().getIdeaId() != null) {
            Idea newIdea = ideaRepository.findById(planDetails.getIdea().getIdeaId())
                    .orElseThrow(() -> new RuntimeException("Idea not found with id " + planDetails.getIdea().getIdeaId()));

            if (foundPlan.getIdea() != null && !foundPlan.getIdea().getIdeaId().equals(newIdea.getIdeaId())) {
                foundPlan.getIdea().setIsConvertedToPlan(false);
                ideaRepository.save(foundPlan.getIdea());
            }

            if (newIdea.getIsConvertedToPlan() == Boolean.TRUE && (foundPlan.getIdea() == null || !foundPlan.getIdea().getIdeaId().equals(newIdea.getIdeaId()))) {
                throw new RuntimeException("New Idea with id " + newIdea.getIdeaId() + " has already been converted to a plan.");
            }

            foundPlan.setIdea(newIdea);
            newIdea.setIsConvertedToPlan(true);
            ideaRepository.save(newIdea);
        } else if (foundPlan.getIdea() != null) {
            foundPlan.getIdea().setIsConvertedToPlan(false);
            ideaRepository.save(foundPlan.getIdea());
            foundPlan.setIdea(null);
        }

        foundPlan.setUpdateDate(Timestamp.from(Instant.now()));

        return planRepository.save(foundPlan);
    }

    @Transactional
    public void delete(Long id){
        Plan planToDelete = planRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plan with id " + id + " not found"));

        if (planToDelete.getIdea() != null) {
            planToDelete.getIdea().setIsConvertedToPlan(false);
            ideaRepository.save(planToDelete.getIdea());
        }

        planRepository.deleteById(id);
    }
}