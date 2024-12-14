package vn.edu.iuh.fit.Services;

import vn.edu.iuh.fit.Models.Candidate;
import vn.edu.iuh.fit.Models.Company;
import vn.edu.iuh.fit.Models.Job;
import vn.edu.iuh.fit.Models.Skill;

import java.util.List;

public interface ServiceInterface {
    public interface CompanyService {
        Company saveCompany(Company company);
        List<Job> getAllJobsByCompany(Long companyId);
        void sendInvitationEmail(Long companyId, Long candidateId);
    }

    public interface CandidateService {
        Candidate saveCandidate(Candidate candidate);
        List<Job> suggestJobsForCandidate(Long candidateId);
        List<Skill> suggestSkillsToLearn(Long candidateId);
    }

    public interface JobService {
        Job createJob(Job job);
        List<Candidate> findCandidatesForJob(Long jobId);
    }

}
