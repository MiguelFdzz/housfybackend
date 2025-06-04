package fdz.migue.housfybackend.service;

import fdz.migue.housfybackend.entity.PlanCategory;
import fdz.migue.housfybackend.entity.House;
import fdz.migue.housfybackend.repository.PlanCategoryRepository;
import fdz.migue.housfybackend.repository.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PlanCategoryService {

    @Autowired
    private PlanCategoryRepository planCategoryRepository;
    @Autowired
    private HouseRepository houseRepository;

    @Transactional(readOnly = true)
    public List<PlanCategory> findAll(){
        return planCategoryRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<PlanCategory> findById(Long id){
        return planCategoryRepository.findById(id);
    }

    @Transactional
    public PlanCategory create(PlanCategory planCategory){
        House house = houseRepository.findById(planCategory.getHouse().getHouseId())
                .orElseThrow(() -> new RuntimeException("House not found with id " + planCategory.getHouse().getHouseId()));
        planCategory.setHouse(house);
        return planCategoryRepository.save(planCategory);
    }

    @Transactional
    public PlanCategory update(PlanCategory planCategory){
        return planCategoryRepository.save(planCategory);
    }

    @Transactional
    public PlanCategory updateById(Long id, PlanCategory planCategoryDetails){
        PlanCategory foundPlanCategory = planCategoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plan Category with id " + id + " not found"));

        foundPlanCategory.setName(planCategoryDetails.getName());

        if (planCategoryDetails.getHouse() != null && planCategoryDetails.getHouse().getHouseId() != null) {
            House house = houseRepository.findById(planCategoryDetails.getHouse().getHouseId())
                    .orElseThrow(() -> new RuntimeException("House not found with id " + planCategoryDetails.getHouse().getHouseId()));
            foundPlanCategory.setHouse(house);
        } else {
            throw new IllegalArgumentException("House ID cannot be null for PlanCategory update.");
        }

        return planCategoryRepository.save(foundPlanCategory);
    }

    @Transactional
    public void delete(Long id){
        if (!planCategoryRepository.existsById(id)) {
            throw new RuntimeException("Plan Category with id " + id + " not found");
        }
        planCategoryRepository.deleteById(id);
    }
}