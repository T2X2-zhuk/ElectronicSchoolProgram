package SchoolLessonsAndCertificates.jpa.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "class_lessons")
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClassLesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "school_class_id", nullable = false)
    private Long schoolClassId;

    @ManyToOne
    @JoinColumn(name = "lesson_id", nullable = false)
    private Lesson lesson;
}
