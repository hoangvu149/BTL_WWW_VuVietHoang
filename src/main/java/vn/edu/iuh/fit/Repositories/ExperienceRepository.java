package vn.edu.iuh.fit.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.iuh.fit.Models.Experience;

public interface ExperienceRepository extends JpaRepository<Experience, Long> {
}