package Intranet.backend.services;

import Intranet.backend.dto.CountType;
import Intranet.backend.entites.Task;
import Intranet.backend.repositories.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskService {
    private TaskRepository taskRepository;

    @Transactional(readOnly = true)
    public List<Task> getTasks(){
        return taskRepository.getAllTaskDueDateDesc();
    }
    @Transactional
    public Task save(Task task) {
        return taskRepository.saveAndFlush(task);
    }
    @Transactional(readOnly = true)
    public boolean existById(Long id) {
        return taskRepository.existsById(id);
    }
    @Transactional(readOnly = true)
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }
    public void delete(Long id) {
        taskRepository.deleteById(id);
    }

    public List<CountType> getPercentageGroupByType() {
        return taskRepository.getPercentageGroupByType();

    }


}
