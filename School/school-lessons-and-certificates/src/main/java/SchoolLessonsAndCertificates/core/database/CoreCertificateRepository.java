package SchoolLessonsAndCertificates.core.database;

import SchoolLessonsAndCertificates.core.domain.CoreCertificate;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CoreCertificateRepository extends JpaRepository<CoreCertificate,Long> {

}
