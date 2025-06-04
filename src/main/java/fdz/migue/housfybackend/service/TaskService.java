package fdz.migue.housfybackend.service;

import fdz.migue.housfybackend.entity.Task;
import fdz.migue.housfybackend.entity.House;
import fdz.migue.housfybackend.entity.TaskCategory;
import fdz.migue.housfybackend.repository.TaskRepository;
import fdz.migue.housfybackend.repository.HouseRepository;
import fdz.migue.housfybackend.repository.TaskCategoryRepository;
import fdz.migue.housfybackend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private HouseRepository houseRepository;
    @Autowired
    private TaskCategoryRepository taskCategoryRepository;
    @Autowired
    private ProductRepository productRepository;


    @Transactional(readOnly = true)
    public List<Task> findAll(){
        return taskRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Task> findById(Long id){
        return taskRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Task> findTasksByHouseId(Long houseId) {
        if (!houseRepository.existsById(houseId)) {
            throw new RuntimeException("House not found with id " + houseId);
        }
        return taskRepository.findByHouse_HouseId(houseId);
    }

    @Transactional(readOnly = true)
    public List<Task> findTasksByCategoryId(Long categoryId) {
        if (!taskCategoryRepository.existsById(categoryId)) {
            throw new RuntimeException("Task Category not found with id " + categoryId);
        }
        return taskRepository.findByCategory_CategoryId(categoryId);
    }

    @Transactional
    public Task create(Task task){
        House house = houseRepository.findById(task.getHouse().getHouseId())
                .orElseThrow(() -> new RuntimeException("House not found with id " + task.getHouse().getHouseId()));
        task.setHouse(house);

        TaskCategory category = taskCategoryRepository.findById(task.getCategory().getCategoryId())
                .orElseThrow(() -> new RuntimeException("Task Category not found with id " + task.getCategory().getCategoryId()));
        task.setCategory(category);

        if (task.getCreationDate() == null) {
            task.setCreationDate(Timestamp.from(Instant.now()));
        }

        return taskRepository.save(task);
    }

    @Transactional
    public Task update(Task task){
        return taskRepository.save(task);
    }

    @Transactional
    public Task updateById(Long id, Task taskDetails){
        Task foundTask = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task with id " + id + " not found"));

        foundTask.setName(taskDetails.getName());
        foundTask.setDescription(taskDetails.getDescription());

        if (taskDetails.getHouse() != null && taskDetails.getHouse().getHouseId() != null) {
            House house = houseRepository.findById(taskDetails.getHouse().getHouseId())
                    .orElseThrow(() -> new RuntimeException("House not found with id " + taskDetails.getHouse().getHouseId()));
            foundTask.setHouse(house);
        } else {
            throw new IllegalArgumentException("House ID cannot be null for Task update.");
        }

        if (taskDetails.getCategory() != null && taskDetails.getCategory().getCategoryId() != null) {
            TaskCategory category = taskCategoryRepository.findById(taskDetails.getCategory().getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Task Category not found with id " + taskDetails.getCategory().getCategoryId()));
            foundTask.setCategory(category);
        } else {
            throw new IllegalArgumentException("Category ID cannot be null for Task update.");
        }


        return taskRepository.save(foundTask);
    }

    @Transactional
    public void delete(Long id){
        if (!taskRepository.existsById(id)) {
            throw new RuntimeException("Task with id " + id + " not found");
        }

        taskRepository.deleteById(id);
    }
}