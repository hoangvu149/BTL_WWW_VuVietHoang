package vn.edu.iuh.fit.frontend.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.backend.Models.Job;
import vn.edu.iuh.fit.backend.Models.Skill;
import vn.edu.iuh.fit.backend.Repositories.CandidateRepository;
import vn.edu.iuh.fit.backend.Services.CandidateService;
import vn.edu.iuh.fit.backend.Services.SkillService;

import java.util.List;

@Controller
@RequestMapping("/candidates")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;
    @Autowired
    private SkillService skillService;
    @Autowired
    private CandidateRepository candidateRepository;

    @GetMapping("/{id}")
    public String getCandidateById(@PathVariable Long id, Model model) {
        model.addAttribute("candidate", candidateService.getCandidateById(id));
        return "candidates/candidateDetail";
    }

    @GetMapping("/")
    public String getAllCandidates(Model model) {
        model.addAttribute("candidates", candidateService.getAllCandidates());
        return "candidates/candidateList";
    }

    @DeleteMapping("/{id}")
    public String deleteCandidate(@PathVariable Long id) {
        candidateService.deleteCandidate(id);
        return "redirect:/candidates/";
    }

    @GetMapping("/{id}/suggested-jobs")
    public String suggestJobsForCandidate(@PathVariable Long id, Model model) {
        List<Job> suggestedJobs = candidateService.suggestJobsForCandidate(id);
        model.addAttribute("suggestedJobs", suggestedJobs);
        return "candidates/suggestedJobs";
    }

    @GetMapping("/{id}/suggested-skills")
    public String suggestSkillsToLearn(@PathVariable Long id, Model model) {
        List<Skill> suggestedSkills = candidateService.suggestSkillsToLearn(id);
        model.addAttribute("suggestedSkills", suggestedSkills);
        return "candidates/suggestedSkills";
    }
}