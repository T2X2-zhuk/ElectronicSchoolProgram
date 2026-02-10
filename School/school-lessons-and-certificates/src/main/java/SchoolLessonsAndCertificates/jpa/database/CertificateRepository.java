package SchoolLessonsAndCertificates.jpa.database;

import SchoolLessonsAndCertificates.jpa.domain.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CertificateRepository  extends JpaRepository<Certificate,Long> {


    @Modifying
    @Transactional
    @Query("DELETE FROM Certificate c WHERE c.studentId = :studentId")
    void deleteByStudentId(@Param("studentId") Long studentId);
}
