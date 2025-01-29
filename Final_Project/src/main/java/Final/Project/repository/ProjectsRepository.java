package Final.Project.repository;

import Final.Project.model.Employee;
import Final.Project.model.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectsRepository extends CrudRepository<Project, String> {
 Optional<Project> findByName(String name);
}