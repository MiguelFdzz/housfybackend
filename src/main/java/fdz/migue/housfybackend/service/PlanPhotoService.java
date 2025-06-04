package fdz.migue.housfybackend.service;

import fdz.migue.housfybackend.entity.PlanPhoto;
import fdz.migue.housfybackend.entity.Plan;
import fdz.migue.housfybackend.entity.User;
import fdz.migue.housfybackend.repository.PlanPhotoRepository;
import fdz.migue.housfybackend.repository.PlanRepository;
import fdz.migue.housfybackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PlanPhotoService {

    @Autowired
    private PlanPhotoRepository planPhotoRepository;
    @Autowired
    private PlanRepository planRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<PlanPhoto> findAll(){
        return planPhotoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<PlanPhoto> findById(Long id){
        return planPhotoRepository.findById(id);
    }

    @Transactional
    public PlanPhoto create(PlanPhoto planPhoto){
        Plan plan = planRepository.findById(planPhoto.getPlan().getPlanId())
                .orElseThrow(() -> new RuntimeException("Plan not found with id " + planPhoto.getPlan().getPlanId()));
        User uploadUser = userRepository.findById(planPhoto.getUploadUser().getUserId())
                .orElseThrow(() -> new RuntimeException("Upload User not found with id " + planPhoto.getUploadUser().getUserId()));

        planPhoto.setPlan(plan);
        planPhoto.setUploadUser(uploadUser);

        return planPhotoRepository.save(planPhoto);
    }

    @Transactional
    public PlanPhoto update(PlanPhoto planPhoto){
        return planPhotoRepository.save(planPhoto);
    }

    @Transactional
    public PlanPhoto updateById(Long id, PlanPhoto planPhotoDetails){
        PlanPhoto foundPlanPhoto = planPhotoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plan Photo with id " + id + " not found"));

        foundPlanPhoto.setPhotoUrl(planPhotoDetails.getPhotoUrl());

        return planPhotoRepository.save(foundPlanPhoto);
    }

    @Transactional
    public void delete(Long id){
        if (!planPhotoRepository.existsById(id)) {
            throw new RuntimeException("Plan Photo with id " + id + " not found");
        }
        planPhotoRepository.deleteById(id);
    }
}
