package Final.Project.service;

import Final.Project.model.Project;
import Final.Project.model.ProjectDTO;
import Final.Project.model.Task;
import Final.Project.model.TaskDTO;
import Final.Project.repository.ProjectsRepository;
import Final.Project.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectsRepository projectRepository;

    public List<TaskDTO> getAllTasks(Long projectId) {
        Project project = projectRepository.findById(String.valueOf(projectId))
                .orElseThrow(() -> new IllegalArgumentException("Project not found with id: " + projectId));

        List<Task> tasks = taskRepository.findByProjectId(project.getId());
        return tasks.stream()
                .map(Task::toDto)
                .toList();
    }

    public TaskDTO getTaskByIdAndProjectId(Long taskId, Long projectId) {
        Project project = new Project();
        project.setId(projectId);

        return taskRepository.findByIdAndProjectId(taskId, projectId)
                .map(Task::toDto) // Преобразование Task в TaskDTO
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Task not found"));
    }

        public void createTask(Long projectId, TaskDTO taskDTO){
            Project project = projectRepository.findById(String.valueOf(projectId))
                    .orElseThrow(()->new IllegalArgumentException("Project not found with id" + projectId));

    Task task = taskDTO.toModel(project);
    task.setProject(project);

    taskRepository.save(task);
    }

    public void updateTask(Long projectId, Long taskId, TaskDTO updateTask) {
        if (!projectRepository.existsById(String.valueOf(projectId))) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Task checkIfTaskExisting = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found"));

        if (checkIfTaskExisting.getProject().getId().equals(projectId)) {
            Task fromDTO = updateTask.toModel(checkIfTaskExisting.getProject());
            fromDTO.setId(taskId);
            taskRepository.save(fromDTO);
        }else
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

    }

        public void deleteTask(Long projectId, Long taskId){
            if (!projectRepository.existsById(String.valueOf(projectId))) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found with id " + projectId);
            }
            if (!taskRepository.existsById(taskId)) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found with id " + taskId);
            }
            Task task = taskRepository.findById(taskId).orElse(null);
            if (task != null && !task.getProject().getId().equals(projectId)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Task does not belong to the specified project");
            }
            taskRepository.delete(task);
        }
}