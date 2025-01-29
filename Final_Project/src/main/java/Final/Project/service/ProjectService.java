package Final.Project.service;
import Final.Project.model.Project;
import Final.Project.model.ProjectDTO;
import Final.Project.repository.ProjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectsRepository projectRepository;

    public List<ProjectDTO> getAllProjects() {
        List<Project> projects = (List<Project>) projectRepository.findAll();
        return projects.stream()
                .map(Project::toDto)
                .toList();
    }

    public ProjectDTO getProjectById(Long projectId) {
        return projectRepository.findById(String.valueOf(projectId))
                .map(Project::toDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found"));
    }

    public ProjectDTO getProjectByName(String name) {
        return projectRepository.findByName(name)
               .map(Project::toDto)
               .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found"));
    }

    public void createProject(ProjectDTO projectDTO) {
        Project project = projectDTO.toModel();
        projectRepository.save(project);
    }

    public void updateProject(Long id, ProjectDTO toUpdate) {

        if(projectRepository.existsById(String.valueOf(id))){
            Project fromDTO = toUpdate.toModel();
            fromDTO.setId(id);
            projectRepository.save(fromDTO);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public void deleteProject(Long projectId) {
        if (!projectRepository.existsById(String.valueOf(projectId))) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found");
        }
        projectRepository.deleteById(String.valueOf(projectId));

    }
}
