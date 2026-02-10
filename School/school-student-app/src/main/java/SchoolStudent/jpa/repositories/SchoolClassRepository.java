package SchoolStudent.jpa.repositories;

import SchoolStudent.jpa.domain.SchoolClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SchoolClassRepository extends JpaRepository<SchoolClass, Long> {

    @Query("SELECT sc FROM SchoolClass sc where sc.category = :category AND sc.number = :number")
    Optional<SchoolClass> findByNumberAndCategory(@Param("number") Long numberClass, @Param("category") String classCategory);
}
