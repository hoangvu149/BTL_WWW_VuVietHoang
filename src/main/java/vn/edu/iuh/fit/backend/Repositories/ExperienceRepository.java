package vn.edu.iuh.fit.backend.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.backend.Models.Experience;
@Repository
public interface ExperienceRepository extends JpaRepository<Experience, Long> {
}