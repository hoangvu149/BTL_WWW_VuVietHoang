package vn.edu.iuh.fit.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.edu.iuh.fit.Models.CandidateSkill;
import vn.edu.iuh.fit.Models.CandidateSkillId;

import java.util.List;

public interface CandidateSkillRepository extends JpaRepository<CandidateSkill, CandidateSkillId> {
    // Lấy danh sách ID kỹ năng của ứng viên
    @Query("SELECT cs.skill.id FROM CandidateSkill cs WHERE cs.candidate.id = :candidateId")
    List<Long> findSkillIdsByCandidateId(@Param("candidateId") Long candidateId);

}