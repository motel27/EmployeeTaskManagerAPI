package Final.Project.controller;

import Final.Project.model.TaskDTO;
import Final.Project.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {

    @Autowired
        private TaskService taskService;

    @GetMapping("/api/projects/{projectId}/tasks")
    public List<TaskDTO> getAllTasks(@PathVariable("projectId") Long projectId){
        return taskService.getAllTasks(projectId);
    }

    @GetMapping("/api/projects/{projectId}/tasks/{taskId}")
    public TaskDTO getTaskByIdAndProjectId(
            @PathVariable("projectId") Long projectId,
            @PathVariable("taskId") Long taskId) {
        return taskService.getTaskByIdAndProjectId(taskId, projectId);
    }
    @PostMapping("/api/projects/{projectId}/tasks")
    public ResponseEntity<String> createProject(@PathVariable Long projectId, @RequestBody TaskDTO taskDTO){
        taskService.createTask(projectId, taskDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Task created in the ProjectId: " + projectId);
    }

    @PutMapping("/api/projects/{projectId}/tasks/{taskId}")
    public ResponseEntity<String> updateProjectTask(
            @PathVariable Long projectId,
            @PathVariable Long taskId,
            @RequestBody TaskDTO taskDTO) {
        taskService.updateTask(projectId, taskId, taskDTO);
        return ResponseEntity.ok("Task updated with id: " + taskId);
    }

    @DeleteMapping("/api/projects/{projectId}/tasks/{taskId}")
    public ResponseEntity<String> deleteProject(@PathVariable Long projectId, @PathVariable Long taskId){
        taskService.deleteTask(projectId, taskId);
        return ResponseEntity.ok("Task deleted with id: " + taskId);
    }

}



