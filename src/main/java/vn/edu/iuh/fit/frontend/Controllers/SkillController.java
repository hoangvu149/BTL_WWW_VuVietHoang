package vn.edu.iuh.fit.frontend.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.backend.Models.Skill;
import vn.edu.iuh.fit.backend.Services.SkillService;

@Controller
@RequestMapping("/skills")
public class SkillController {

    @Autowired
    private SkillService skillService;

    @GetMapping("/create")
    public String showSkillForm(Model model) {
        model.addAttribute("skill", new Skill());
        return "skills/skillForm";
    }

    @PostMapping("/create")
    public String createSkill(@ModelAttribute Skill skill) {
        skillService.addSkill(skill);
        return "redirect:/skills";
    }

    @GetMapping
    public String listSkills(Model model) {
        model.addAttribute("skills", skillService.getAllSkills());
        return "skills/skillList";
    }

    @GetMapping("/delete/{id}")
    public String deleteSkill(@PathVariable Long id) {
        skillService.deleteSkill(id);
        return "redirect:/skills";
    }
}