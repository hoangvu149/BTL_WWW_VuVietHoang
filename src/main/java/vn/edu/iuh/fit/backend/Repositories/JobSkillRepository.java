package vn.edu.iuh.fit.backend.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.backend.Models.JobSkill;
import vn.edu.iuh.fit.backend.Models.JobSkillId;
@Repository
public interface JobSkillRepository extends JpaRepository<JobSkill, JobSkillId> {
}