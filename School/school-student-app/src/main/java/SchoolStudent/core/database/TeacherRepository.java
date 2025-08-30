package SchoolStudent.core.database;

import SchoolStudent.core.domain.SchoolClass;
import SchoolStudent.core.domain.SchoolStudent;
import SchoolStudent.core.domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher,Long> {


    Optional<Teacher> findBysubject(String subject);

    Optional<Teacher> findByemail(String email);
    Optional<Teacher> findBypassword(String password);

    @Query("Select t FROM Teacher t left join t.classes_id ci where ci.number =:number AND ci.category = :category")
    Optional<Teacher> findTeacherBySchoolClass(@Param("number") Long number, @Param("category") String category);

    @Modifying
    @Transactional
    @Query("DELETE FROM Teacher t WHERE t.password = :password")
    void deleteByPassword(@Param("password") String password);

    @Modifying
    @Query(value = "UPDATE Teacher t SET t.classes_id = :new_classes_id where t.email = :email")
    void forUpdateTeacherData(@Param("email") String email, @Param("new_classes_id") SchoolClass new_classes_id);

}
