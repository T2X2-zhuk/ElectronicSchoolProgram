package acceptanceTests.schoolStudent.schoolClass;

import acceptanceTest.schoolLessonsAndCertificates.dto.LessonDTO;
import acceptanceTest.schoolStudent.dto.SchoolClassDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import restControllers.schoolLessonAndCertificates.CleanSchoolLessonsAndCertificatesDbRestController;
import restControllers.schoolLessonAndCertificates.CreateClassLessonsRestController;
import restControllers.schoolStudent.CleanSchoolStudentsDbRestController;
import restControllers.schoolStudent.schoolClass.CreateSchoolClassRestController;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;

public class CreateSchoolClassRestControllerTest {

//    @BeforeEach
    public void cleanDB(){
        CleanSchoolStudentsDbRestController.execute(true,true,true,false);
        CleanSchoolLessonsAndCertificatesDbRestController.execute(true,true,false);
    }

//    @Test
    public void acceptanceTest(){
        SchoolClassDTO schoolClassDTO = getSchoolClassDTO(2L,"B");
        CreateSchoolClassRestController.execute(schoolClassDTO).then().statusCode(200)
                .body("message",equalTo("School class successful created."));
        CreateSchoolClassRestController.execute(schoolClassDTO).then().statusCode(200)
                .body("errors[0].errorCode",equalTo("SCHOOL_STUDENT_ERROR_CODE_21"))
                .body("errors[0].description",equalTo("Such school class already exist!"));
    }

    private static SchoolClassDTO getSchoolClassDTO(Long number, String category) {
        return SchoolClassDTO.builder()
                .number(number)
                .category(category).build();
    }
}
