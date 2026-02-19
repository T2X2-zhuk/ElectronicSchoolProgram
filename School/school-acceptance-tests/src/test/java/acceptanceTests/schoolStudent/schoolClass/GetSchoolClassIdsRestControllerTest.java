package acceptanceTests.schoolStudent.schoolClass;

import acceptanceTest.schoolLessonsAndCertificates.dto.LessonDTO;
import acceptanceTest.schoolStudent.dto.SchoolClassDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import restControllers.schoolLessonAndCertificates.CleanSchoolLessonsAndCertificatesDbRestController;
import restControllers.schoolLessonAndCertificates.CreateClassLessonsRestController;
import restControllers.schoolStudent.CleanSchoolStudentsDbRestController;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;

public class GetSchoolClassIdsRestControllerTest {

//  @BeforeEach
    public void cleanDB(){
        CleanSchoolStudentsDbRestController.execute(true,true,true,false);
        CleanSchoolLessonsAndCertificatesDbRestController.execute(true,true,false);
    }

//  @Test
    public void acceptanceTest(){
        SchoolClassDTO schoolClassDTO = getSchoolClassDTO(2L,"B");
        SchoolClassDTO schoolClassDTO2 = getSchoolClassDTO(3L,"A");
        SchoolClassDTO schoolClassDTO3 = getSchoolClassDTO(2L,"A");
        LessonDTO lessonDTO = LessonDTO.builder().name("Estonian language").build();
        CreateClassLessonsRestController.execute(List.of(schoolClassDTO,schoolClassDTO2,schoolClassDTO3),lessonDTO)
                .then().statusCode(200)
                .body("errors[0].errorCode",equalTo("SCHOOL_STUDENT_ERROR_CODE_24"))
                .body("errors[0].description",equalTo("Of the classes you entered, none could be found in the database!"));
    }

    private static SchoolClassDTO getSchoolClassDTO(Long number, String category) {
        return SchoolClassDTO.builder()
                .number(number)
                .category(category).build();
    }
}
