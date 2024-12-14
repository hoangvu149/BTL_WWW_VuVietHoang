package vn.edu.iuh.fit.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.edu.iuh.fit.Models.Candidate;

import java.util.List;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    @Query("SELECT DISTINCT c FROM Candidate c " +
            "JOIN c.skills cs " +
            "WHERE cs.skill.id IN :skillIds")
    List<Candidate> findCandidatesBySkillIds(@Param("skillIds") List<Long> skillIds);
}