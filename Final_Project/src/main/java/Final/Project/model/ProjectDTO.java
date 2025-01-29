package Final.Project.model;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ProjectDTO {

    private Long id;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private Employee createdBy;

    public Employee getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Employee createdBy) {
        this.createdBy = createdBy;
    }

    public Project toModel() {
        Project project = new Project();
        // Debug logging
        System.out.println("Start date: " + this.startDate);
        System.out.println("End date: " + this.endDate);

        project.setId(this.id);
        project.setName(this.name);
        project.setDescription(this.description);

        if (this.startDate != null) {
            project.setStartDate(this.startDate.atStartOfDay());
        } else {
            throw new IllegalArgumentException("Start date cannot be null");
        }

        if (this.endDate != null) {
            project.setEndDate(this.endDate.atStartOfDay());
        } else {
            throw new IllegalArgumentException("End date cannot be null");
        }
        return project;

    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
