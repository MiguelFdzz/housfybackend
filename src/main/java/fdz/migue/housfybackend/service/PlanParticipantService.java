package fdz.migue.housfybackend.service;

import fdz.migue.housfybackend.entity.PlanParticipant;
import fdz.migue.housfybackend.entity.PlanParticipantId;
import fdz.migue.housfybackend.entity.Plan;
import fdz.migue.housfybackend.entity.User;
import fdz.migue.housfybackend.repository.PlanParticipantRepository;
import fdz.migue.housfybackend.repository.PlanRepository;
import fdz.migue.housfybackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PlanParticipantService {

    @Autowired
    private PlanParticipantRepository planParticipantRepository;
    @Autowired
    private PlanRepository planRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<PlanParticipant> findAll(){
        return planParticipantRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<PlanParticipant> findById(PlanParticipantId id){
        return planParticipantRepository.findById(id);
    }

    @Transactional
    public PlanParticipant create(PlanParticipant planParticipant){
        Plan plan = planRepository.findById(planParticipant.getId().getPlanId())
                .orElseThrow(() -> new RuntimeException("Plan not found with id " + planParticipant.getId().getPlanId()));
        User user = userRepository.findById(planParticipant.getId().getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id " + planParticipant.getId().getUserId()));

        planParticipant.setPlan(plan);
        planParticipant.setUser(user);

        return planParticipantRepository.save(planParticipant);
    }

    @Transactional
    public PlanParticipant update(PlanParticipant planParticipant){
        return planParticipantRepository.save(planParticipant);
    }

    @Transactional
    public PlanParticipant updateById(PlanParticipantId id, PlanParticipant planParticipantDetails){
        PlanParticipant foundParticipant = planParticipantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plan Participant for Plan ID " + id.getPlanId() + " and User ID " + id.getUserId() + " not found"));

        foundParticipant.setConfirmed(planParticipantDetails.getConfirmed());
        foundParticipant.setPlan(planParticipantDetails.getPlan());

        return planParticipantRepository.save(foundParticipant);
    }

    @Transactional
    public void delete(PlanParticipantId id){
        if (!planParticipantRepository.existsById(id)) {
            throw new RuntimeException("Plan Participant for Plan ID " + id.getPlanId() + " and User ID " + id.getUserId() + " not found");
        }
        planParticipantRepository.deleteById(id);
    }
}
