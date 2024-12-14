package vn.edu.iuh.fit.backend.Models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "skill")
public class Skill implements Serializable {
    private static final long serialVersionUID = -3660215936568803694L;
    @Id
    @Column(name = "skill_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "skill_name", nullable = false, length = 150)
    private String skillName;

    @Column(name = "skill_desc")
    private String skillDesc;

    @Column(name = "skill_type")
    private Byte skillType;

    public Skill(String skillName) {
    }
}