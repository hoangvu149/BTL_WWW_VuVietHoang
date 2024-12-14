package vn.edu.iuh.fit.frontend.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.backend.Models.Candidate;
import vn.edu.iuh.fit.backend.Models.Job;
import vn.edu.iuh.fit.backend.Models.Skill;
import vn.edu.iuh.fit.backend.Services.JobService;
import vn.edu.iuh.fit.backend.Services.SkillService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JobService jobService;
    @Autowired
    private SkillService skillService;

    @GetMapping("/create")
    @PreAuthorize("hasRole('RECRUITER')")
    public String showJobForm(Model model) {
        model.addAttribute("job", new Job());
        model.addAttribute("skills", skillService.getAllSkills());
        return "jobs/jobForm";
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('RECRUITER')")
    public String createJob(@ModelAttribute Job job, @RequestParam("jobSkills") List<Long> skillIds, Model model) {
        try {
            List<Skill> selectedSkills = skillIds.stream()
                    .map(skillId -> skillService.getSkillById(skillId))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            job.setJobSkills(selectedSkills);
            jobService.addJob(job);
            return "redirect:/jobs";
        } catch (Exception e) {
            model.addAttribute("error", "Error creating job: " + e.getMessage());
            model.addAttribute("job", job);
            model.addAttribute("skills", skillService.getAllSkills());
            return "jobs/jobForm";
        }
    }

    @GetMapping("/view/{id}")
    public String viewJob(@PathVariable Long id, Model model) {
        model.addAttribute("job", jobService.getJobById(id).orElse(null));
        return "jobs/jobDetail";
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasRole('RECRUITER')")
    public String editJobForm(@PathVariable Long id, Model model) {
        model.addAttribute("job", jobService.getJobById(id).orElse(null));
        model.addAttribute("skills", skillService.getAllSkills());
        return "jobs/jobForm";
    }

    @PostMapping("/edit/{id}")
    @PreAuthorize("hasRole('RECRUITER')")
    public String editJob(@PathVariable Long id, @ModelAttribute Job job, @RequestParam("jobSkills") List<Long> skillIds, Model model) {
        try {
            List<Skill> selectedSkills = skillIds.stream()
                    .map(skillId -> skillService.getSkillById(skillId))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            job.setJobSkills(selectedSkills);
            job.setId(id);
            jobService.addJob(job);
            return "redirect:/jobs";
        } catch (Exception e) {
            model.addAttribute("error", "Error editing job: " + e.getMessage());
            model.addAttribute("job", job);
            model.addAttribute("skills", skillService.getAllSkills());
            return "jobs/jobForm";
        }
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('RECRUITER')")
    public String deleteJob(@PathVariable Long id) {
        jobService.deleteJob(id);
        return "redirect:/jobs";
    }

    @GetMapping("/apply/{id}")
    @PreAuthorize("hasRole('CANDIDATE')")
    public String applyJob(@PathVariable Long id, @AuthenticationPrincipal Candidate candidate) {
        jobService.applyForJob(id, candidate);
        return "redirect:/jobs";
    }

    @GetMapping("")
    public String getAllJobs(Model model) {
        model.addAttribute("jobs", jobService.getAllJobs());
        return "jobs/jobList";
    }
}