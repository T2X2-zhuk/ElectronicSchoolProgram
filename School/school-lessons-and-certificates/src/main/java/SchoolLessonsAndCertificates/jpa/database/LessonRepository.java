package SchoolLessonsAndCertificates.jpa.database;
import SchoolLessonsAndCertificates.jpa.domain.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LessonRepository extends JpaRepository<Lesson,Long>{

    @Query("SELECT l FROM Lesson l WHERE LOWER(l.name) = LOWER(:name)")
    Optional<Lesson> findByName(@Param("name") String name);
}
