package SchoolStudent.jpa.repositories;

import SchoolStudent.jpa.domain.SchoolClass;
import SchoolStudent.jpa.domain.SchoolStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SchoolStudentRepository extends JpaRepository<SchoolStudent,Long>{


    boolean existsBySchoolClass_NumberAndSchoolClass_Category(
            Long number,
            String category);

    List<SchoolStudent> findAllBySchoolClass_Id(Long id);

    Optional<SchoolStudent> findByEmail(String email);
    Optional<SchoolStudent> findByPassword(String password);

    boolean existsByPassword(String password);
    boolean existsByEmail(String password);

    @Modifying
    @Query("DELETE FROM SchoolStudent st WHERE st.email = :email")
    void deleteByEmail(@Param("email") String email);

    @Modifying
    @Query("""
   UPDATE SchoolStudent st
   SET st.schoolClass = :newClass
   WHERE st.email = :email
""")
    void updateStudentClass(
            @Param("email") String email,
            @Param("newClass") SchoolClass newClass);
}
