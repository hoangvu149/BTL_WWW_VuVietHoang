package vn.edu.iuh.fit.backend.Models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "job")
public class Job implements Serializable {
    private static final long serialVersionUID = -1472502076166025655L;
    @Id
    @Column(name = "job_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "job_desc", nullable = false, length = 2000)
    private String jobDesc;

    @Column(name = "job_name", nullable = false)
    private String jobName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company")
    private Company company;

    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JobSkill> jobSkills; // Đây là quan hệ với bảng trung gian JobSkill

    public void setJobSkills(List<Skill> skillList) {
        if (this.jobSkills == null) {
            this.jobSkills = new ArrayList<>();
        }
        for (Skill skill : skillList) {
            JobSkill jobSkill = new JobSkill(this, skill);
            this.jobSkills.add(jobSkill);
        }
    }



//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(
//            name = "job_skills",
//            joinColumns = @JoinColumn(name = "job_id"),
//            inverseJoinColumns = @JoinColumn(name = "skill_id")
//    )
//    private List<Skill> listSkills;
}