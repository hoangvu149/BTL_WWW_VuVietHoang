package vn.edu.iuh.fit.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.edu.iuh.fit.Models.Job;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {
    // Lấy danh sách công việc của một công ty
    @Query("SELECT j FROM Job j WHERE j.company.id = :companyId")
    List<Job> findByCompanyId(@Param("companyId") Long companyId);

    // Tìm các công việc yêu cầu kỹ năng có trong danh sách kỹ năng của ứng viên
    @Query("SELECT DISTINCT j FROM Job j " +
            "JOIN j.jobSkills js " +
            "WHERE js.skill.id IN :skillIds")
    List<Job> findJobsByRequiredSkills(@Param("skillIds") List<Long> skillIds);


}