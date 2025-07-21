package SchoolStudent.core.database;

import SchoolStudent.core.domain.SpecificCodeForRegistrationInElectronicSchool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CodeForRegistrationRepository extends JpaRepository<SpecificCodeForRegistrationInElectronicSchool,Long>{

    @Query("SELECT ss.specific_code_for_registration_for_student FROM SpecificCodeForRegistrationInElectronicSchool ss where specific_code_for_registration_for_student =:specific_code_for_registration_for_student")
    Optional<SpecificCodeForRegistrationInElectronicSchool> findByspecificCodeForRegistrationForStudent(@Param("specific_code_for_registration_for_student") String specific_code_for_registration_for_student);

    @Query("SELECT ss.specific_code_for_registration_for_teacher FROM SpecificCodeForRegistrationInElectronicSchool ss where specific_code_for_registration_for_teacher =:specific_code_for_registration_for_teacher")
    Optional<SpecificCodeForRegistrationInElectronicSchool> findByspecificCodeForRegistrationForTeacher(@Param("specific_code_for_registration_for_teacher")String specificCodeForRegistrationForTeacher);

}
