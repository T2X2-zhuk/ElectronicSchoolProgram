package SchoolLessonsAndCertificates.jpa.database;

import SchoolLessonsAndCertificates.jpa.domain.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CertificateRepository  extends JpaRepository<Certificate,Long> {

    @Modifying
    @Query("DELETE FROM Certificate c WHERE c.studentId IN :studentIds")
    void deleteByStudentIds(@Param("studentIds") List<Long> studentIds);

    boolean existsByStudentIdAndClassLesson_Id(Long studentId,Long classLessonId);
    boolean existsByStudentIdAndClassLesson_SchoolClassId(
            Long studentId,
            Long schoolClassId
    );
}
