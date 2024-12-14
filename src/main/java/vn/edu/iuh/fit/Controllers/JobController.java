package vn.edu.iuh.fit.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.Models.Job;
import vn.edu.iuh.fit.Models.Skill;
import vn.edu.iuh.fit.Services.JobService;
import vn.edu.iuh.fit.Services.SkillService;

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
    public String showJobForm(Model model) {
        model.addAttribute("job", new Job());
        model.addAttribute("skills", skillService.getAllSkills());
        return "jobs/jobForm";
    }



    @PostMapping("/create")
    public String createJob(@ModelAttribute Job job, @RequestParam("jobSkills") List<Long
            > skillIds, Model model) {
        try {
            // Lấy danh sách Skill từ danh sách skillIds được gửi từ form
            List<Skill> selectedSkills = skillIds.stream()
                    .map(skillId -> skillService.getSkillById(skillId))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

            // Set danh sách các kỹ năng vào job
            job.setJobSkills(selectedSkills);

            // Lưu job vào cơ sở dữ liệu
            jobService.addJob(job);

            // Điều hướng đến danh sách các công việc sau khi tạo thành công
            return "redirect:/jobs";
        } catch (Exception e) {
            // Thêm thông báo lỗi vào model nếu có lỗi xảy ra
            model.addAttribute("error", "Error creating job: " + e.getMessage());
            model.addAttribute("job", job);
            model.addAttribute("skills", skillService.getAllSkills());
            return "jobs/jobForm";
        }
    }



    @GetMapping("/{id}")
    public String getJobById(@PathVariable Long id, Model model) {
        model.addAttribute("job", jobService.getJobById(id).orElse(null));
        return "jobs/jobDetail";
    }

    @GetMapping("")
    public String getAllJobs(Model model) {
        model.addAttribute("jobs", jobService.getAllJobs());
        return "jobs/jobList";
    }

    @DeleteMapping("/{id}")
    public String deleteJob(@PathVariable Long id) {
        jobService.deleteJob(id);
        return "redirect:/jobs/";
    }
}