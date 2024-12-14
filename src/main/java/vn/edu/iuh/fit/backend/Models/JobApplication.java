package vn.edu.iuh.fit.backend.Models;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "job_application")
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    // Getters and setters...
}