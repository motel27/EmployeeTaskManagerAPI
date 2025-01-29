package Final.Project.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TaskDTO {

    private Long id;
    private String name;
    private String description;
    private String status;
    private LocalDate dueDate;
    private LocalDate createdDate;
    private Long projectId;

    public Task toModel(Project project) {
        Task task = new Task();
        task.setName(this.name);
        task.setDescription(this.description);
        task.setStatus(this.status);
        task.setProject(project);
        if (this.dueDate != null) {
            task.setDueDate(this.dueDate.atStartOfDay());
        }
        if (this.createdDate != null) {
            task.setCreatedDate(this.createdDate.atStartOfDay());
        } else {
            task.setCreatedDate(LocalDateTime.now());
        }
        return task;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public CharSequence getTitle() {
        return null;
    }
}
