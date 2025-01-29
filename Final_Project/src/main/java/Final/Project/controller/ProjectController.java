package Final.Project.controller;

import Final.Project.model.Employee;
import Final.Project.model.Project;
import Final.Project.model.ProjectDTO;
import Final.Project.repository.ProjectsRepository;
import Final.Project.service.EmployeeService;
import Final.Project.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProjectController {

    @Autowired
        private ProjectService projectService;

    @GetMapping("api/projects")
    public List<ProjectDTO> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("api/projects/{projectId}")
    public ProjectDTO getProjectById(@PathVariable Long projectId) {
        return projectService.getProjectById(projectId);
    }

    @GetMapping("api/projects/name/{projectName}")
    public ProjectDTO getProjectByName(@PathVariable String projectName) {
        return projectService.getProjectByName(projectName);
    }

    @PostMapping("api/projects")
    public ResponseEntity<String> createProject(@RequestBody ProjectDTO projectDTO) {
        projectService.createProject(projectDTO);
        ResponseEntity.ok("Project Created");
        return ResponseEntity.status(HttpStatus.CREATED).body("Project created");
    }

    @PutMapping("api/projects/{projectId}")
    public ResponseEntity<String> updateProject(@PathVariable("projectId") Long id, @RequestBody @Valid ProjectDTO toUpdate){

        projectService.updateProject(id, toUpdate);
        return ResponseEntity.ok("Project updated");
    }

    @DeleteMapping("api/projects/{projectId}")
    public ResponseEntity<String> deleteProject(@PathVariable Long projectId) {
        projectService.deleteProject(projectId);
        return ResponseEntity.ok("Project deleted with id: " + projectId);
    }
}
