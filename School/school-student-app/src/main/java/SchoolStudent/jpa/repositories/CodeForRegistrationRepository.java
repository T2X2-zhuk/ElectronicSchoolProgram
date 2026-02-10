package SchoolStudent.jpa.repositories;

import SchoolStudent.jpa.domain.SpecificCodeForRegistrationInElectronicSchool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CodeForRegistrationRepository extends JpaRepository<SpecificCodeForRegistrationInElectronicSchool,Long>{

    @Query("SELECT ss.specificCodeForRegistrationForStudent FROM SpecificCodeForRegistrationInElectronicSchool ss where specificCodeForRegistrationForStudent =:specificCodeForRegistrationForStudent")
    Optional<SpecificCodeForRegistrationInElectronicSchool> findBySpecificCodeForRegistrationForStudent(@Param("specificCodeForRegistrationForStudent") String specificCodeForRegistrationForStudent);

    @Query("SELECT ss.specificCodeForRegistrationForTeacher FROM SpecificCodeForRegistrationInElectronicSchool ss where specificCodeForRegistrationForTeacher =:specificCodeForRegistrationForTeacher")
    Optional<SpecificCodeForRegistrationInElectronicSchool> findBySpecificCodeForRegistrationForTeacher(@Param("specificCodeForRegistrationForTeacher")String specificCodeForRegistrationForTeacher);

}
