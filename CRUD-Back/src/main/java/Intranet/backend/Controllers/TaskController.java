package Intranet.backend.Controllers;

import Intranet.backend.dto.CountType;
import Intranet.backend.entites.Task;
import Intranet.backend.services.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
public class TaskController {
    private TaskService taskservice;

    @GetMapping("/task/percentcounttype")
    public List<CountType> getPercentageGroupByType(){
        return taskservice.getPercentageGroupByType();
    }


    @GetMapping("/task")
    public List<Task> getTask(){
        return taskservice.getTasks();
    }

    @PostMapping("/task")
    public Task addTask(@RequestBody Task task) {
        return taskservice.save(task);
    }

    @GetMapping("/task/{id}")
    public Task getById(@PathVariable Long id) {
        return taskservice.getTaskById(id).orElseThrow(()->new EntityNotFoundException("Requested Task not found"));
    }

    @PutMapping("/task/{id}")
    public ResponseEntity<?> addTask(@RequestBody Task taskPara,@PathVariable Long id) {
        if(taskservice.existById(id)) {
            Task task=taskservice.getTaskById(id).orElseThrow(()->new EntityNotFoundException("Requested Task not found"));
            task.setTitle(taskPara.getTitle());
            task.setDueDate(taskPara.getDueDate());
            task.setType(taskPara.getType());
            task.setDescription(taskPara.getDescription());
            taskservice.save(task);
            return ResponseEntity.ok().body(task);
        }else {
            HashMap<String, String> message= new HashMap<>();
            message.put("message", id + " task not found or matched");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }

    @DeleteMapping("/task/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        if(taskservice.existById(id)) {
            taskservice.delete(id);
            HashMap<String, String>message= new HashMap<>();
            message.put("message", id + " task removed");
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }else {
            HashMap<String, String>message= new HashMap<>();
            message.put("message", id + " task not found or matched");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
    }
}
