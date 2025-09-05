package SchoolLessonsAndCertificates.core.database;

import SchoolLessonsAndCertificates.core.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student,Long> {


    @Query("Select st FROM Student st  where st.first_name =:first_name AND st.last_name = :last_name AND st.fatherland = :fatherland")
    Optional<Student> findByFirstNameLastNameFatherland(@Param("first_name") String first_name, @Param("last_name") String last_name, @Param("fatherland") String fatherland);

}
