package Final.Project.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @JsonProperty("name")
    private String name;

    @Column(nullable = false)
    @JsonProperty("description")
    private String description;

    @Column(nullable = false)
    @JsonProperty("status")
    private String status;

    @JsonProperty("due_date")
    @Column(name = "due_date", nullable = false)
    private LocalDateTime dueDate;

    @JsonProperty("created_at")
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdDate;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    @JsonProperty("project_id")
    private Project project;


    public TaskDTO toDto() {
        TaskDTO dto = new TaskDTO();
        dto.setId(this.id);
        dto.setName(this.name);
        dto.setDescription(this.description);
        dto.setStatus(this.status);
        dto.setDueDate(this.dueDate.toLocalDate());
        dto.setCreatedDate(this.createdDate.toLocalDate());
        dto.setProjectId(this.project != null ? this.project.getId() : null);
        return dto;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
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

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }


}
