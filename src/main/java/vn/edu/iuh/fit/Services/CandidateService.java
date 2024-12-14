package vn.edu.iuh.fit.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.Models.Candidate;
import vn.edu.iuh.fit.Models.Job;
import vn.edu.iuh.fit.Models.Skill;
import vn.edu.iuh.fit.Repositories.CandidateRepository;
import vn.edu.iuh.fit.Repositories.CandidateSkillRepository;
import vn.edu.iuh.fit.Repositories.JobRepository;
import vn.edu.iuh.fit.Repositories.SkillRepository;

import java.util.List;

@Service
public class CandidateService implements ServiceInterface.CandidateService{

    @Autowired
    private CandidateRepository candidateRepository;

    public CandidateService(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public CandidateRepository getCandidateRepository() {
        return candidateRepository;
    }

//    private void deleteCandidate(Long id) {
//        candidateRepository.deleteById(id);
//    }
//
//    private Candidate getCandidateById(Long id) {
//        return candidateRepository.findById(id).orElse(null);
//    }
//
//    private List<Candidate> getAllCandidates() {
//        return candidateRepository.findAll();
//    }


    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private CandidateSkillRepository candidateSkillRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Override
    public Candidate saveCandidate(Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    @Override
    public List<Job> suggestJobsForCandidate(Long candidateId) {
        List<Long> candidateSkillIds = candidateSkillRepository.findSkillIdsByCandidateId(candidateId);
        return jobRepository.findJobsByRequiredSkills(candidateSkillIds);
    }

    @Override
    public List<Skill> suggestSkillsToLearn(Long candidateId) {
        List<Long> candidateSkillIds = candidateSkillRepository.findSkillIdsByCandidateId(candidateId);
        return skillRepository.findSkillsNotIn(candidateSkillIds);
    }
    public List<Candidate> getAllCandidates() {
        return candidateRepository.findAll();
    }

    public Page<Candidate> findAll(int pageNo, int pageSize, String sortBy, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        return candidateRepository.findAll(pageable);
    }

    public Candidate getCandidateById(Long id) {
        return candidateRepository.findById(id).orElse(null);
    }

    public void deleteCandidate(Long id) {
        candidateRepository.deleteById(id);
    }
}

