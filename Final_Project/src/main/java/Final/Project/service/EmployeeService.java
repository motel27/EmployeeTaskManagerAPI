package Final.Project.service;
import Final.Project.controller.UpdatePasswordRequest;
import Final.Project.model.Employee;
import Final.Project.model.EmployeeDTO;
import Final.Project.repository.EmployeeRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;


    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = (List<Employee>) employeeRepository.findAll();
        return employees.stream()
                .map(Employee::toDto)
                .toList();
    }

    public EmployeeDTO getEmployeeByUsername(String username) {
        return employeeRepository.findByUsername(username)
                .map(Employee::toDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));
    }

    public void registerEmployee(EmployeeDTO employeeDTO) {
        if (employeeRepository.findByUsername(employeeDTO.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Employee with username" + employeeDTO.getUsername() + "already exists");
        }
        Employee employee = employeeDTO.toModel();
        employeeRepository.save(employee);
    }

    public void updateEmployeeDescription(Long id, EmployeeDTO toUpdate) {
        if (employeeRepository.existsById(String.valueOf(id))) {
            Employee fromDTO = toUpdate.toModel();
            fromDTO.setId(id);
            employeeRepository.save(fromDTO);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found");
        }
    }

    public void updateEmployeePassword(Long id, EmployeeDTO toUpdate){
        if(employeeRepository.existsById(String.valueOf(id))){
            Employee fromDTO = toUpdate.toModel();
            fromDTO.setId(id);
            employeeRepository.save(fromDTO);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found");
        }
    }

    public void deleteEmployee(String name) {
        Employee employee = employeeRepository.findByUsername(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));
        employeeRepository.delete(employee);
    }

}
