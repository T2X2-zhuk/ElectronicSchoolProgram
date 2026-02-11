package SchoolStudent.jpa.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "specificCodeForRegistrationInSchool")
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

    @Column(name = "specificCodeForRegistrationForStudent",nullable = false)
    private String specificCodeForRegistrationForStudent;

    @Column(name = "specificCodeForRegistrationForTeacher",nullable = false)
    private String specificCodeForRegistrationForTeacher;
}
