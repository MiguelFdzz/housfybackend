package fdz.migue.housfybackend.service;

import fdz.migue.housfybackend.entity.TaskCategory;
import fdz.migue.housfybackend.entity.House;
import fdz.migue.housfybackend.repository.TaskCategoryRepository;
import fdz.migue.housfybackend.repository.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TaskCategoryService {

    @Autowired
    private TaskCategoryRepository taskCategoryRepository;
    @Autowired
    private HouseRepository houseRepository;

    @Transactional(readOnly = true)
    public List<TaskCategory> findAll(){
        return taskCategoryRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<TaskCategory> findById(Long id){
        return taskCategoryRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<TaskCategory> findTaskCategoriesByHouseId(Long houseId) {
        if (!houseRepository.existsById(houseId)) {
            throw new RuntimeException("House not found with id " + houseId);
        }
        return taskCategoryRepository.findByHouse_HouseId(houseId);
    }

    @Transactional
    public TaskCategory create(TaskCategory taskCategory){
        House house = houseRepository.findById(taskCategory.getHouse().getHouseId())
                .orElseThrow(() -> new RuntimeException("House not found with id " + taskCategory.getHouse().getHouseId()));
        return taskCategoryRepository.save(taskCategory);
    }

    @Transactional
    public TaskCategory update(TaskCategory taskCategory){
        return taskCategoryRepository.save(taskCategory);
    }

    @Transactional
    public TaskCategory updateById(Long id, TaskCategory taskCategoryDetails){
        TaskCategory foundCategory = taskCategoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task Category with id " + id + " not found"));

        foundCategory.setName(taskCategoryDetails.getName());

        if (taskCategoryDetails.getHouse() != null && taskCategoryDetails.getHouse().getHouseId() != null) {
            House house = houseRepository.findById(taskCategoryDetails.getHouse().getHouseId())
                    .orElseThrow(() -> new RuntimeException("House not found with id " + taskCategoryDetails.getHouse().getHouseId()));
            foundCategory.setHouse(house);
        } else {
            throw new IllegalArgumentException("House ID cannot be null for TaskCategory update.");
        }

        return taskCategoryRepository.save(foundCategory);
    }

    @Transactional
    public void delete(Long id){
        if (!taskCategoryRepository.existsById(id)) {
            throw new RuntimeException("Task Category with id " + id + " not found");
        }
        taskCategoryRepository.deleteById(id);
    }
}