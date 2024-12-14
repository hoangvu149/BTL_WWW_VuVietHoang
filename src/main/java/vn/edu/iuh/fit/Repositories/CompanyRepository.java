package vn.edu.iuh.fit.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.iuh.fit.Models.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}