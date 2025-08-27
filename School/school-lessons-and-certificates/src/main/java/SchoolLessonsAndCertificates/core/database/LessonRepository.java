package SchoolLessonsAndCertificates.core.database;
import SchoolLessonsAndCertificates.core.domain.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
public interface LessonRepository extends JpaRepository<Lesson,Long>{

    @Query("Select l FROM Lesson l where l.name =:name")
    List<Lesson> findByname(@Param("name") String name);

}
