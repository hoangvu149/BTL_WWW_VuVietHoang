package vn.edu.iuh.fit.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.Models.Candidate;
import vn.edu.iuh.fit.Models.Company;
import vn.edu.iuh.fit.Models.Job;
import vn.edu.iuh.fit.Repositories.CandidateRepository;
import vn.edu.iuh.fit.Repositories.CompanyRepository;
import vn.edu.iuh.fit.Repositories.JobRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService implements ServiceInterface.CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private EmailService emailService; // Custom email sending service

    @Override
    public Company saveCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public List<Job> getAllJobsByCompany(Long companyId) {
        return jobRepository.findByCompanyId(companyId);
    }

    @Override
    public void sendInvitationEmail(Long companyId, Long candidateId) {
        Optional<Company> companyOpt = companyRepository.findById(companyId);
        Optional<Candidate> candidateOpt = candidateRepository.findById(candidateId);

        if (companyOpt.isPresent() && candidateOpt.isPresent()) {
            String emailBody = String.format("Dear %s, %s invites you to apply for their job.",
                    candidateOpt.get().getFullName(), companyOpt.get().getCompName());
            emailService.sendEmail(candidateOpt.get().getEmail(), "Job Invitation", emailBody);
        }
    }
}
