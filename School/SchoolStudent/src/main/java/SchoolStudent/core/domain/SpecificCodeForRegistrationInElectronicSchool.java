package SchoolStudent.core.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name = "specific_code_for_registration_in_school")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SpecificCodeForRegistrationInElectronicSchool {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "specific_code_for_registration_for_student",nullable = false)
    private String specific_code_for_registration_for_student;

    @Column(name = "specific_code_for_registration_for_teacher",nullable = false)
    private String specific_code_for_registration_for_teacher;
}
