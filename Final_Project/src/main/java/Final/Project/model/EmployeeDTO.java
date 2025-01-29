package Final.Project.model;

import java.time.LocalDateTime;

public class EmployeeDTO {

    private Long id;
    private String username;
    private String email;
    private String createdAt;
    private String password;

    public Employee toModel() {
        Employee employee = new Employee();
        employee.setId(this.id);
        employee.setUsername(this.username);
        employee.setEmail(this.email);
        employee.setPassword(this.password);

        if (this.createdAt != null) {
            employee.setCreatedAt(LocalDateTime.parse(this.createdAt));
        }

        return employee;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = String.valueOf(createdAt);
    }
}
