package SchoolStudent.jpa.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "specific_code_for_registration_in_school")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpecificCodeForRegistrationInElectronicSchool {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "specific_code_for_registration_for_student",nullable = false)
    private String specificCodeForRegistrationForStudent;

    @Column(name = "specific_code_for_registration_for_teacher",nullable = false)
    private String specificCodeForRegistrationForTeacher;
}
