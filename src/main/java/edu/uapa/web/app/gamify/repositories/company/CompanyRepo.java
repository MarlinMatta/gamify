package edu.uapa.web.app.gamify.repositories.company;

import edu.uapa.web.app.gamify.domains.company.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepo extends JpaRepository<Company, Long> {
}
