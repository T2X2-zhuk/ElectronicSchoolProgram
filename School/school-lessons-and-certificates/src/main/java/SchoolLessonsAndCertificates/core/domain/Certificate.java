package SchoolLessonsAndCertificates.core.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "certificates")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Certificate {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "lesson_id", nullable = false)
    private Lesson lesson_id;

    @Column(name = "first_trimester")
    private Long first_trimester;

    @Column(name = "two_trimester")
    private Long two_trimester;

    @Column(name = "three_trimester")
    private Long three_trimester;

    @Column(name = "annual_subject_assessment")
    private Long annual_subject_assessment;

}
