package SchoolLessonsAndCertificates.jpa.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "certificates")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Certificate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_id", nullable = false)
    private Long studentId;

    @ManyToOne
    @JoinColumn(name = "class_lessons_id", nullable = false)
    private ClassLesson classLesson;

    @Column(name = "first_trimester")
    private Long firstTrimester;

    @Column(name = "two_trimester")
    private Long twoTrimester;

    @Column(name = "three_trimester")
    private Long threeTrimester;

    @Column(name = "annual_subject_assessment")
    private Long annualSubjectAssessment;
}