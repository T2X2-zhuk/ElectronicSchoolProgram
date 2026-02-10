package SchoolLessonsAndCertificates.jpa.database;

import SchoolLessonsAndCertificates.jpa.domain.ClassLesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassLessonRepository extends JpaRepository<ClassLesson,Long> {


}
