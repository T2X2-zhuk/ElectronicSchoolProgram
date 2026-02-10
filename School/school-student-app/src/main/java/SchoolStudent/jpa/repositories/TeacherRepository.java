package SchoolStudent.jpa.repositories;

import SchoolStudent.jpa.domain.SchoolClass;
import SchoolStudent.jpa.domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher,Long> {

    Optional<Teacher> findByEmail(String email);
    Optional<Teacher> findByPassword(String password);

    Optional<Teacher> findBySchoolClassNumberAndSchoolClassCategory(Long number,String category);

    @Modifying
    @Transactional
    @Query("DELETE FROM Teacher t WHERE t.email = :email")
    void deleteByEmail(@Param("email") String email);

    @Modifying
    @Transactional
    @Query("""
   UPDATE Teacher t
   SET t.schoolClass = :newClass
   WHERE t.email = :email
""")
    void updateTeacherClass(
            @Param("email") String email,
            @Param("newClass") SchoolClass newClass);

}
