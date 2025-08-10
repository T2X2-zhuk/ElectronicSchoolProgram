package SchoolStudent.core.database;

import SchoolStudent.core.domain.SchoolClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SchoolClassRepository  extends JpaRepository<SchoolClass,Long>{

    @Query("SELECT sc.number FROM SchoolClass sc WHERE sc.number = :number")
   List<Long> findAllNumberWhichEqualsThisParameter(@Param("number") Long number);

    @Query("SELECT sc FROM SchoolClass sc WHERE sc.number = :number")
    List<SchoolClass> findByNumber(@Param("number") Long number);

    @Query("SELECT sc FROM SchoolClass sc where sc.category = :category")
    List<SchoolClass> findBycategory(@Param("category") String category);

    @Query("SELECT sc FROM SchoolClass sc where sc.category = :category AND sc.number = :number")
  SchoolClass findBynumberAndcategory(@Param("number") Long numberClass , @Param("category") String classCategory);
}
