package vn.edu.iuh.fit.backend.Models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "experience")
public class Experience implements Serializable {
    private static final long serialVersionUID = 4342638353023905582L;
    @Id
    @Column(name = "exp_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "from_date", nullable = false)
    private LocalDate fromDate;

    @Column(name = "to_date")
    private LocalDate toDate;

    @Column(name = "role", length = 400)
    private String role;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "company", nullable = false)
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "can_id", nullable = false)
    private Candidate can;

}