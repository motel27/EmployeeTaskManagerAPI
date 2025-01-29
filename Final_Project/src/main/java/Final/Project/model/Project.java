package Final.Project.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @JsonProperty("name")
    private String name;

    @Column(nullable = false, unique = true)
    @JsonProperty("description")
    private String description;

    @JsonProperty("start_date")
    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @JsonProperty("end_date")
    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    @JsonProperty("created_by")
    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private Employee createdBy;


    public ProjectDTO toDto() {
        ProjectDTO dto = new ProjectDTO();
        dto.setId(this.id);
        dto.setName(this.name);
        dto.setDescription(this.description);
        dto.setStartDate(this.startDate.toLocalDate());
        dto.setEndDate(this.endDate.toLocalDate());
        dto.setCreatedBy(this.createdBy);
        return dto;
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

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Employee getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Employee createdBy) {
        this.createdBy = createdBy;
    }
}