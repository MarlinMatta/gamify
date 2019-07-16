package edu.uapa.web.app.gamify.repositories.company;

import edu.uapa.web.app.gamify.domains.company.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, Long> {
}
