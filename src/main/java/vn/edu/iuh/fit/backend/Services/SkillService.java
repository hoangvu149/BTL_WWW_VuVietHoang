package vn.edu.iuh.fit.backend.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.backend.Models.Skill;
import vn.edu.iuh.fit.backend.Repositories.SkillRepository;

import java.util.List;

@Service
public class SkillService {

    @Autowired
    private SkillRepository skillRepository;

    public SkillService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    public Skill addSkill(Skill skill) {
        return skillRepository.save(skill);
    }

    public Skill updateSkill(Skill skill) {
        return skillRepository.save(skill);
    }

    public void deleteSkill(Long id) {
        skillRepository.deleteById(id);
    }

    public Skill getSkillById(Long id) {
        return skillRepository.findById(id).orElse(null);
    }

    public Object getSkillBySkillName(String skillName) {
        return skillRepository.findBySkillName(skillName);
    }
}