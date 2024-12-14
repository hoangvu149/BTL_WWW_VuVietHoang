package vn.edu.iuh.fit.backend.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.iuh.fit.backend.Models.Skill;

import java.util.List;
@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
    // Tìm các kỹ năng mà ứng viên chưa có (để đề xuất học thêm)
    @Query("SELECT s FROM Skill s WHERE s.id NOT IN :skillIds")
    List<Skill> findSkillsNotIn(@Param("skillIds") List<Long> skillIds);

    // Tìm kỹ năng theo tên

    Skill findBySkillName(String skillName);
}