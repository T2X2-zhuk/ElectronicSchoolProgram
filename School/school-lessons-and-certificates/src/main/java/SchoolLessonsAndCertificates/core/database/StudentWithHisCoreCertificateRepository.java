package SchoolLessonsAndCertificates.core.database;

import SchoolLessonsAndCertificates.core.domain.StudentWithHisCoreCertificate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentWithHisCoreCertificateRepository extends JpaRepository<StudentWithHisCoreCertificate,Long> {


}
