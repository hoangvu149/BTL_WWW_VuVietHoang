package vn.edu.iuh.fit.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.iuh.fit.Models.JobSkill;
import vn.edu.iuh.fit.Models.JobSkillId;

public interface JobSkillRepository extends JpaRepository<JobSkill, JobSkillId> {
}