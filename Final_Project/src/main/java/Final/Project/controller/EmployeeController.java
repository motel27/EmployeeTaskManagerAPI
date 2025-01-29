package Final.Project.controller;
import Final.Project.model.Employee;
import Final.Project.model.EmployeeDTO;
import Final.Project.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("api/users/profile")
    public List<EmployeeDTO> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/api/user/profile/{username}")
    public EmployeeDTO getAllEmployeeByName(@PathVariable String username) {
        return employeeService.getEmployeeByUsername(username);
    }

    @PostMapping("api/register")
    public ResponseEntity<String> registerEmployee(@RequestBody EmployeeDTO employeeDTO) {
        try {
            employeeService.registerEmployee(employeeDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Employee was registered");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PutMapping("/api/user/profile/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable Long id, @RequestBody @Valid EmployeeDTO employeeDTO) {
        employeeService.updateEmployeeDescription(id, employeeDTO);
        return ResponseEntity.ok("Employee updated successfully");
    }

    @PatchMapping("/api/user/password/{id}")
    public ResponseEntity<String> updateEmployeePassword(@PathVariable Long id, @RequestBody @Valid EmployeeDTO employeeDTO) {
        employeeService.updateEmployeeDescription(id, employeeDTO);
        return ResponseEntity.ok("Employee updated successfully");
    }

    @DeleteMapping("/api/user/profile/{name}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("name") String name) {
        employeeService.deleteEmployee(name);
        return ResponseEntity.ok("Deleted");
    }

}