package acceptanceTests.lessonAndCertificates.classLesson;

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

public class CreateClassLessonsRestControllerTest {

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
        SchoolClassDTO schoolClassDTO2 = getSchoolClassDTO(3L,"A");
        CreateSchoolClassRestController.execute(schoolClassDTO2).then().statusCode(200)
                .body("message",equalTo("School class successful created."));
        SchoolClassDTO schoolClassDTO3 = getSchoolClassDTO(2L,"A");
        CreateSchoolClassRestController.execute(schoolClassDTO3).then().statusCode(200)
                .body("message",equalTo("School class successful created."));
        LessonDTO lessonDTO = LessonDTO.builder().name("Estonian language").build();
        CreateClassLessonsRestController.execute(List.of(schoolClassDTO,schoolClassDTO2,schoolClassDTO3),lessonDTO)
                .then().statusCode(200)
                .body("message",equalTo("Class lessons successfully created."));
    }

//    @Test
    public void acceptanceTest2(){
        LessonDTO lessonDTO = LessonDTO.builder().name("").build();
        CreateClassLessonsRestController.execute(List.of(),lessonDTO)
                .then().statusCode(200)
                .body("errors[0].errorCode",equalTo("LESSONS_AND_CERTIFICATES_ERROR_CODE_2"))
                .body("errors[0].description",equalTo("Lesson name must not be empty!"))
                .body("errors[1].errorCode",equalTo("LESSONS_AND_CERTIFICATES_ERROR_CODE_3"))
                .body("errors[1].description",equalTo("You haven't entered a school class!"));
    }

//    @Test
    public void acceptanceTest3(){
        SchoolClassDTO schoolClassDTO1 = getSchoolClassDTO(2L,"B");
        SchoolClassDTO schoolClassDTO2 = getSchoolClassDTO(2L,"B");
        SchoolClassDTO schoolClassDTO3 = getSchoolClassDTO(2L,"B");
        LessonDTO lessonDTO = LessonDTO.builder().name("hsddsgghz").build();
        CreateClassLessonsRestController.execute(List.of(schoolClassDTO1,schoolClassDTO2,schoolClassDTO3),lessonDTO)
                .then().statusCode(200)
                .body("errors[0].errorCode",equalTo("LESSONS_AND_CERTIFICATES_ERROR_CODE_1"))
                .body("errors[0].description",equalTo("There is no such lesson!"));
    }

//    @Test
    public void acceptanceTest4(){
        SchoolClassDTO schoolClassDTO1 = getSchoolClassDTO(1L,"B");
        CreateSchoolClassRestController.execute(schoolClassDTO1).then().statusCode(200)
                .body("message",equalTo("School class successful created."));
        SchoolClassDTO schoolClassDTO2 = getSchoolClassDTO(1L,"A");
        CreateSchoolClassRestController.execute(schoolClassDTO2).then().statusCode(200)
                .body("message",equalTo("School class successful created."));
        SchoolClassDTO schoolClassDTO3 = getSchoolClassDTO(1L,"C");
        CreateSchoolClassRestController.execute(schoolClassDTO3).then().statusCode(200)
                .body("message",equalTo("School class successful created."));
        LessonDTO lessonDTO = LessonDTO.builder().name("Estonian language").build();

        CreateClassLessonsRestController.execute(List.of(schoolClassDTO1,schoolClassDTO2,schoolClassDTO3),lessonDTO)
                .then().statusCode(200)
                .body("message",equalTo("Class lessons successfully created."));

        CreateClassLessonsRestController.execute(List.of(schoolClassDTO1,schoolClassDTO2,schoolClassDTO3),lessonDTO)
                .then().statusCode(200)
                .body("message",equalTo("All selected classes already contain this lesson."));
    }

    private static SchoolClassDTO getSchoolClassDTO(Long number, String category) {
        return SchoolClassDTO.builder()
                .number(number)
                .category(category).build();
    }
}
