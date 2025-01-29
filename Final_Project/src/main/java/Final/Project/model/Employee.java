package Final.Project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Getter
@Setter
@Table(name = "employee")
public class Employee {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@Column(nullable = false)
@JsonProperty("username")
    private String username;

@Column(nullable = false, unique = true)
@JsonProperty("email")
    private String email;

@Column(nullable = false)
@JsonProperty("password")
    private String password;

@JsonProperty("created_at")
@Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

public EmployeeDTO toDto() {
    EmployeeDTO dto = new EmployeeDTO();
    dto.setId(this.id);
    dto.setUsername(this.username);
    dto.setEmail(this.email);
    dto.setCreatedAt(this.createdAt);
    dto.setPassword(this.password);
    return dto;
}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
