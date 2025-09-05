package SchoolLessonsAndCertificates.core.database;

import SchoolLessonsAndCertificates.core.domain.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificateRepository  extends JpaRepository<Certificate,Long> {

}
