package SchoolStudent.core.database;

import SchoolStudent.core.domain.SchoolClass;
import SchoolStudent.core.domain.SchoolStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface SchoolStudentRepository extends JpaRepository<SchoolStudent,Long>{

    @Query("Select st FROM SchoolStudent st left join st.classes_id ci where ci.number =:number AND ci.category = :category")
     List<SchoolStudent> findAllSchoolStudentByTheGivenClassParameter(@Param("number") Long number, @Param("category") String category);

    @Query("Select st FROM SchoolStudent st where st.first_name =:first_name")
    List<SchoolStudent> findByfirst_name(@Param("first_name") String first_name);
    @Query("Select st FROM SchoolStudent st where st.last_name =:last_name")
    List<SchoolStudent> findBylast_name(@Param("last_name")String last_name);
    Optional<SchoolStudent> findByfatherland(String fatherland);

    Optional<SchoolStudent> findByemail(String email);
    Optional<SchoolStudent> findBypassword(String password);

    @Modifying
    @Transactional
    @Query("DELETE FROM SchoolStudent st WHERE st.password = :password")
    void deleteByPassword(@Param("password") String password);

    @Modifying
    @Query(value = "UPDATE SchoolStudent st SET st.classes_id = :new_classes_id where st.email = :email")
   void forUpdateStudentData(@Param("email") String email,@Param("new_classes_id") SchoolClass new_classes_id);


}
