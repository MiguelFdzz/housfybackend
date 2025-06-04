package fdz.migue.housfybackend.service;

import fdz.migue.housfybackend.entity.AsignationTask;
import fdz.migue.housfybackend.entity.AsignationTaskId;
import fdz.migue.housfybackend.entity.Task;
import fdz.migue.housfybackend.entity.User;
import fdz.migue.housfybackend.repository.AsignationTaskRepository;
import fdz.migue.housfybackend.repository.TaskRepository;
import fdz.migue.housfybackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AsignationTaskService {

    @Autowired
    private AsignationTaskRepository asignationTaskRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<AsignationTask> findAll(){
        return asignationTaskRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<AsignationTask> findById(AsignationTaskId id){
        return asignationTaskRepository.findById(id);
    }

    @Transactional
    public AsignationTask create(AsignationTask asignationTask){
        Task task = taskRepository.findById(asignationTask.getId().getTaskId())
                .orElseThrow(() -> new RuntimeException("Task not found with id " + asignationTask.getId().getTaskId()));
        User user = userRepository.findById(asignationTask.getId().getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id " + asignationTask.getId().getUserId()));

        asignationTask.setTask(task);
        asignationTask.setUser(user);

        return asignationTaskRepository.save(asignationTask);
    }

    @Transactional
    public AsignationTask save(AsignationTask asignationTask){
        return asignationTaskRepository.save(asignationTask);
    }

    @Transactional
    public AsignationTask updateById(AsignationTaskId id, AsignationTask asignationTaskDetails){
        AsignationTask foundAsignationTask = asignationTaskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asignation Task with id " + id.getTaskId() + ", " + id.getUserId() + " not found"));

        foundAsignationTask.setAssignDate(asignationTaskDetails.getAssignDate());

        return asignationTaskRepository.save(foundAsignationTask);
    }

    @Transactional
    public void delete(AsignationTaskId id){
        if (!asignationTaskRepository.existsById(id)) {
            throw new RuntimeException("Asignation Task with id " + id.getTaskId() + ", " + id.getUserId() + " not found");
        }
        asignationTaskRepository.deleteById(id);
    }
}