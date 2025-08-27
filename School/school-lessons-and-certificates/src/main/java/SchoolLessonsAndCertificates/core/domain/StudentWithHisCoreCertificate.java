package SchoolLessonsAndCertificates.core.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "student_with_his_core_certificates")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentWithHisCoreCertificate {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name",nullable = false)
    private String first_name;

    @Column(name = "last_name",nullable = false)
    private String last_name;

    @Column(name = "fatherland",nullable = false)
    private String fatherland;

    @Column(name = "email",nullable = false)
    private String email;

    @ManyToOne
    @JoinColumn(name = "core_certificate_id", nullable = false)
    private CoreCertificate core_certificate_id;

}
