package vn.edu.iuh.fit.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.Models.Candidate;
import vn.edu.iuh.fit.Models.Job;
import vn.edu.iuh.fit.Repositories.CandidateRepository;
import vn.edu.iuh.fit.Repositories.JobRepository;
import vn.edu.iuh.fit.Repositories.JobSkillRepository;

import java.util.List;
import java.util.Optional;

@Service
public class JobService {

    private final JobRepository jobRepository;
    private final JobSkillRepository jobSkillRepository;
    private final CandidateRepository candidateRepository;
    private final EmailService emailService;

    @Autowired
    public JobService(JobRepository jobRepository,
                      JobSkillRepository jobSkillRepository,
                      CandidateRepository candidateRepository, EmailService emailService) {
        this.jobRepository = jobRepository;
        this.jobSkillRepository = jobSkillRepository;
        this.candidateRepository = candidateRepository;
        this.emailService = emailService;
    }

    /**
     * Lưu một công việc mới vào cơ sở dữ liệu
     */
    public Job addJob(Job job) {
        return jobRepository.save(job);
    }

    /**
     * Lấy danh sách tất cả công việc
     */
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    /**
     * Lấy thông tin chi tiết của một công việc theo ID
     */
    public Optional<Job> getJobById(Long jobId) {
        return jobRepository.findById(jobId);
    }

    /**
     * Xóa một công việc theo ID
     */
    public void deleteJob(Long jobId) {
        if (jobRepository.existsById(jobId)) {
            jobRepository.deleteById(jobId);
        } else {
            throw new RuntimeException("Job not found with id: " + jobId);
        }
    }

    /**
     * Gợi ý công việc phù hợp cho ứng viên dựa trên kỹ năng
     */
    public List<Job> suggestJobsForCandidate(Long candidateId) {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new RuntimeException("Candidate not found with id: " + candidateId));

        List<Long> candidateSkillIds = candidate.getSkills().stream()
                .map(candidateSkill -> candidateSkill.getSkill().getId())
                .toList();

        return jobRepository.findJobsByRequiredSkills(candidateSkillIds);
    }

    /**
     * Tìm ứng viên phù hợp với một công việc dựa trên kỹ năng yêu cầu
     */
    public List<Candidate> findCandidatesForJob(Long jobId) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found with id: " + jobId));

        List<Long> jobSkillIds = job.getJobSkills().stream()
                .map(jobSkill -> jobSkill.getSkill().getId())
                .toList();

        return candidateRepository.findCandidatesBySkillIds(jobSkillIds);
    }


    /**
     * Gửi email mời ứng viên cho một công việc.
     * @param jobId       ID của công việc.
     * @param candidateIds Danh sách ID ứng viên.
     */
    public void inviteCandidatesForJob(Long jobId, List<Long> candidateIds) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found with id: " + jobId));

        for (Long candidateId : candidateIds) {
            Candidate candidate = candidateRepository.findById(candidateId)
                    .orElseThrow(() -> new RuntimeException("Candidate not found with id: " + candidateId));

            // Gửi email mời
            String subject = "Invitation for Job: " + job.getJobName();
            String body = "Dear " + candidate.getFullName() + ",\n\n" +
                    "We are excited to invite you to apply for the job: " + job.getJobName() + ".\n\n" +
                    "Best regards,\n" +
                    job.getCompany().getCompName();

            emailService.sendEmail(candidate.getEmail(), subject, body);
        }
    }
}
