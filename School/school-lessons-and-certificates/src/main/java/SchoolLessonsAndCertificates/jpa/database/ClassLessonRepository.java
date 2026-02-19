package SchoolLessonsAndCertificates.jpa.database;

import SchoolLessonsAndCertificates.jpa.domain.ClassLesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassLessonRepository extends JpaRepository<ClassLesson,Long> {


    List<ClassLesson> findBySchoolClassId(Long schoolClassId);

    boolean existsBySchoolClassId(Long schoolClassId);
    boolean existsBySchoolClassIdAndLesson_Name(Long schoolClassId, String lessonName);
}
